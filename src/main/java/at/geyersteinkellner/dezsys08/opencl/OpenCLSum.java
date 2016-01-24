package at.geyersteinkellner.dezsys08.opencl;


import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.opencl.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

import static org.lwjgl.opencl.CL10.*;
import static org.lwjgl.opencl.CLUtil.checkCLError;
import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.system.MemoryUtil.memDecodeUTF8;

public class OpenCLSum {
    // The OpenCL kernel
    static final String source =
            "kernel void sum(global const float *a, global const float *b, global float *answer) { "
                    + "  unsigned int xid = get_global_id(0); "
                    + "  answer[xid] = a[xid] + b[xid];"
                    + "}";

    private static final CLContextCallback CREATE_CONTEXT_CALLBACK = new CLContextCallback() {
        @Override
        public void invoke(long errinfo, long private_info, long cb, long user_data) {
            System.err.println("[LWJGL] cl_create_context_callback");
            System.err.println("\tInfo: " + memDecodeUTF8(errinfo));
        }
    };

    public static void main(String[] args) throws Exception {

    	int durchlaeufe = 10000;
    	int device = CL_DEVICE_TYPE_GPU; // GPU | CPU
    	
    	int length = 10000;
    	
    	float[] temp = new float[length];
    	for(int i=0;i<temp.length;i++) temp[i] = i;
    	FloatBuffer a = toFloatBuffer(temp);
    	
    	temp = new float[length];
    	for(int i=0;i<temp.length;i++) temp[i] = temp.length - i;
    	FloatBuffer b = toFloatBuffer(temp);
    	
    	FloatBuffer answer = BufferUtils.createFloatBuffer(a.capacity());

        System.setProperty("org.lwjgl.opencl.explicitInit", "true");


        // Initialize OpenCL and create a context and command queue
//        CL.create();
        System.out.println("CL created");

        CLPlatform platform = CLPlatform.getPlatforms().get(0);
        System.out.println("Platform created");

        PointerBuffer ctxProps = BufferUtils.createPointerBuffer(3);
        ctxProps.put(CL_CONTEXT_PLATFORM).put(platform).put(0).flip();
        System.out.println("CTX created");


        IntBuffer errcode_ret = BufferUtils.createIntBuffer(1);
        System.out.println("ERRCODE created");


        List<CLDevice> devices = platform.getDevices(device);
        // long context = clCreateContext(platform, devices, null, null, null);
        long context = clCreateContext(ctxProps, devices.get(0).address(), CREATE_CONTEXT_CALLBACK, NULL, errcode_ret);
        System.out.println("CONTEXT created");


        checkCLError(errcode_ret);
        //CLCommandQueue queue = clCreateCommandQueue(context, devices.get(0), CL_QUEUE_PROFILING_ENABLE, null);
        long queue = clCreateCommandQueue(context, devices.get(0).address(), CL_QUEUE_PROFILING_ENABLE, errcode_ret);
        System.out.println("Command Q created");

        // Allocate memory for our two input buffers and our result buffer
        long aMem = clCreateBuffer(context, CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR, a, null);
        //long buffer = clCreateBuffer(context, CL_MEM_READ_ONLY, 128, errcode_ret);
        System.out.println("A Buffer created");
        clEnqueueWriteBuffer(queue, aMem, 1, 0, a, null, null);
        long bMem = clCreateBuffer(context, CL_MEM_READ_ONLY | CL_MEM_COPY_HOST_PTR, b, null);
        System.out.println("B Buffer created");
        clEnqueueWriteBuffer(queue, bMem, 1, 0, b, null, null);
        long answerMem = clCreateBuffer(context, CL_MEM_WRITE_ONLY | CL_MEM_COPY_HOST_PTR, answer, null);
        System.out.println("OUTPUT Buffer created");
        clFinish(queue);

        // Create our program and kernel
        long program = clCreateProgramWithSource(context, source, null);
        System.out.println("PROGRAM created");

        //public static int clBuildProgram(long program, long device, CharSequence options, CLProgramCallback pfn_notify, long user_data) {


        CLUtil.checkCLError(clBuildProgram(program, devices.get(0).address(), "", null, 0L));
        // sum has to match a kernel method name in the OpenCL source
        long kernel = clCreateKernel(program, "sum", null);
        System.out.println("KERNEL created");

        // Execution our kernel
        PointerBuffer kernel1DGlobalWorkSize = BufferUtils.createPointerBuffer(1);
        System.out.println("KERNEL work size created");
        kernel1DGlobalWorkSize.put(0, a.capacity());
        System.out.println("KERNEL work size copied");

        clSetKernelArg1p(kernel, 0, aMem);
        clSetKernelArg1p(kernel, 1, bMem);
        clSetKernelArg1p(kernel, 2, answerMem);

        System.out.println("Args send to kernel");

        clEnqueueNDRangeKernel(queue, kernel, 1, null, kernel1DGlobalWorkSize, null, null, null);
        System.out.println("KERNEL queued created");

        long before = System.nanoTime();
        
        // Read the results memory back into our result buffer
        for(int i=0;i<durchlaeufe;i++){
            clEnqueueReadBuffer(queue, answerMem, 1, 0, answer, null, null);
        }
        System.out.println("and output ... created");
        
       	long time = System.nanoTime() - before;

        clFinish(queue);
        
//        System.out.println(convert(a) + "\n+\n" + convert(b) + "\n=\n" + convert(answer));
        System.out.println("Average: " + time/durchlaeufe + " nanoSeconds!");

        // Clean up OpenCL resources
        clReleaseKernel(kernel);
        clReleaseProgram(program);
        clReleaseMemObject(aMem);
        clReleaseMemObject(bMem);
        clReleaseMemObject(answerMem);
        clReleaseCommandQueue(queue);
        clReleaseContext(context);
        CL.destroy();
    }


    /**
     * Utility method to convert float array to float buffer
     *
     * @param floats the float array to convert
     * @return a float buffer containing the input float array
     */
    static FloatBuffer toFloatBuffer(float[] floats) {
        FloatBuffer buf = BufferUtils.createFloatBuffer(floats.length).put(floats);
        buf.rewind();
        return buf;
    }


    /**
     * Utility method to convert a float buffer to a String
     *
     * @param buffer the float buffer to print to System.out
     * @return buffer as String
     */
    static String convert(FloatBuffer buffer) {
    	String text = "";
        for (int i = 0; i < buffer.capacity(); i++) {
            text += buffer.get(i) + "\t";
        }
        return text;
    }

}