package ak_ss.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import ak_ss.common.Default;
import ak_ss.common.StreamManager;
import ak_ss.common.Tools;

/**
 * 
 * @author SSteinkellner, Akoelbl
 * @version 23.02.2016
 *
 */
public class Controller {
	public Controller(){
		this(Default.PORT);
	}
	
	public Controller(int port){
		try {
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Standalone Service auf " + ss + " gestartet");
			run(new StreamManager(ss.accept()));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public Controller(String balancer){
		this(balancer,Default.PORT+1);
	}
	
	public Controller(String balancer, int port){
		try {
			Socket s = new Socket(balancer,port);
			System.out.println("Service balanciert durch " + s);
			run(new StreamManager(s));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private void run(StreamManager sm){
		Task t = new Task();
		System.out.println("Connection successfull!");
			
		String msg;
		while((msg = sm.read()) != null){
			sm.write(t.pi(msg));
		}
	}
	
	public static void main(String[] args) {
		if(args.length == 0){
			new Controller();
			return;
		}
		
		if(args.length == 1){
			if(args[0].equalsIgnoreCase("help")){
				printUsage();
				return;
			}
			
			int port = Tools.isPort(args[0]);
			if(port == Tools.NOT_NUMERIC){
				new Controller(args[0]);
				return;
			}else if(port == Tools.NOT_PORT){
				System.err.println("invalid portnumber!");
			}else{
				new Controller(port);
				return;
			}
		}
		
		if(args.length == 2){
			int port = Tools.isPort(args[1]);
			if(port == Tools.NOT_PORT){
				System.err.println("invalid portnumber!");
			}else if(port != Tools.NOT_NUMERIC){
				new Controller(args[0], port);
				return;
			}
		}
		
		printUsage();
	}
	
	public static void printUsage(){
		System.out.println("Usage: Service [<port>] | [<balancer> [<port>]]\n");
		System.out.println("If you provide a port, the Service will launch on this port instead of the default port (" + Default.PORT + ")");
		System.out.println("If you provide the adress of a balancer and a port, the Service will connect to the balancer");
	}
}
