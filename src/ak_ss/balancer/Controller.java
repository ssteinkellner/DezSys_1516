package ak_ss.balancer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

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
	private ServerSocket cserver, nserver;
	private LinkedList<Node> nodes;
	private Algorithm algorithm;
	
	private static boolean running;
	
	public Controller(){
		this(Default.PORT);
	}
	
	public Controller(int port){
		this(port, AlgorithmFactory.DEFAULT);
	}
	
	public Controller(int port, String algName){
		nodes = new LinkedList<Node>();
		
		algorithm = AlgorithmFactory.loadAlgorithm(algName, nodes);
		if(algorithm == null){
			System.err.println("selected Algorithm not available!");
			return;
		}

		running = true;
		new Thread(() -> {
			try {
				nserver = new ServerSocket(port+1);
				System.out.println("opened " + nserver + " for Nodes");
				
				while(running){
					Socket connection = nserver.accept();
					System.out.println("New Service on " + connection);
					Node n = new Node(connection);
					
					n.onClose(() -> {
						System.out.println(n + " closed");
						nodes.remove(n);
					});
					
					nodes.add(n);
					new Thread(n).start();
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}).start();
		
		try {
			cserver = new ServerSocket(port);
			System.out.println("opened " + cserver + " for Clients");
			
			while(running){
				StreamManager sm = new StreamManager(cserver.accept());
				System.out.println("New Client on " + sm.getSocket());
				
				new Thread(() -> {
					while(sm.isOpen()){
						String cmd = sm.read();
						if(cmd != null){
							Node n = algorithm.getNext();
							if(n == null){
								sm.write("NoNodeException: Service Unavailable!");
							}else{
								Task t = new Task(cmd);

								t.onFinish(() -> {
									sm.write(t.getResult());
									System.out.println(t + " finished");
								});
								n.addTask(t);
							}
						}
					}
				}).start();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		try{
			nserver.close();
			cserver.close();
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
		
		if(args.length == 2){
			int port = Tools.isPort(args[0]);
			if(port == Tools.NOT_PORT){
				System.err.println("invalid portnumber!");
			}else if(port != Tools.NOT_NUMERIC){
				new Controller(port, args[1]);
				return;
			}
		}
		
		
		printUsage();
	}
	
	public static void printUsage(){
		System.out.println("Usage: balanancer [<port> [<algorithm>]]");
	}
}
