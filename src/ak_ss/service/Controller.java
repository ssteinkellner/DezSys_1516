package ak_ss.service;

import java.io.IOException;
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
	private StreamManager sm;

	public Controller(){
		this(Default.PORT);
	}
	
	public Controller(int port){
		System.out.println(port);
	}
	
	public Controller(String balancer){
		this(balancer,Default.PORT);
	}
	
	public Controller(String balancer, int port){
		Service s = new PiService();
		
		try {
			sm = new StreamManager(new Socket(balancer,port));
			System.out.println("Connection successfull!");
			
			String msg;
			while((msg = sm.read()) != null){
				sm.write(s.serve(msg));
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
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
				new Controller(args[0], Integer.parseInt(args[1]));
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
