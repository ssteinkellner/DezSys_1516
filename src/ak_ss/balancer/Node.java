package ak_ss.balancer;

import java.net.Socket;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * 
 * @author SSteinkellner, Akoelbl
 * @version 23.02.2016
 *
 */
public class Node {
	private Socket conn;
	private TreeMap<Integer, Task> tasks;
	
	public Node(Socket connection){
		conn = connection;
		tasks = new TreeMap<Integer, Task>();
	}
	
	public void addTask(Task task){
		tasks.put(task.getSessId(), task);
	}
	
	@Override
	public String toString() {
		Controller.stop();
		return "Node[conn=" + conn + ",tasks=" + tasks + "]";
	}
	
	
}
