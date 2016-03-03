package ak_ss.balancer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import ak_ss.common.Default;
import ak_ss.common.Tools;

/**
 * 
 * @author SSteinkellner, Akoelbl
 * @version 23.02.2016
 *
 */
public class Controller {
	private ServerSocket server;
	private LinkedList<Socket> clients;
	private LinkedList<Node> nodes;
	private Algorithm algorithm;
	
	private static boolean running;
	
	public Controller(){
		this(Default.PORT);
	}
	
	public Controller(int port){
/*		algorithm = new AlgorithmFactory.loadAlgorithm(name, nodes);
		if(algorithm == null){
			System.err.println("specified Algorithm not available!");
			return;
		}*/
		
		clients = new LinkedList();
		nodes   = new LinkedList();
		
		try {
			server = new ServerSocket(port);
			
			running = true;
			while(running){
				Socket connection = server.accept();
				
				// Node n = algorithm.getNext();
				
				Node n = new Node(connection);
				n.addTask(new Task("test1"));
				n.addTask(new Task("test2"));
				System.out.println(n);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public static void stop(){
		running = false;
	}
	
	public static boolean isRunning(){
		return running;
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
			if(port == Tools.NOT_PORT){
				System.err.println("invalid portnumber!");
			}else if(port != Tools.NOT_NUMERIC){
				new Controller(port);
				return;
			}
		}
		
		printUsage();
	}
	
	public static void printUsage(){
		System.out.println("Usage: balanancer [<port>]");
	}
}
