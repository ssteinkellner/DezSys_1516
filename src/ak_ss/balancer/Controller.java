package ak_ss.balancer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

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
		this(1204);
	}
	
	public Controller(int port){
/*		algorithm = new AlgorithmFactory.loadAlgorithm(name, nodes);
		if(algorithm == null){
			System.err.println("selected Algorithm not available!");
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
				n.addTask(new Task("test2"));
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

			try{
				new Controller(Integer.parseInt(args[0]));
				return;
			}catch(NumberFormatException nfe){
				System.out.println("caught: " + nfe);
			}
		}
		
		printUsage();
	}
	
	public static void printUsage(){
		System.out.println("Usage: balanancer [<port>]");
	}
}
