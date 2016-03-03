package ak_ss.balancer;

import java.net.Socket;
import java.util.Collection;
import java.util.TreeMap;

import ak_ss.common.StreamManager;

/**
 * 
 * @author SSteinkellner, Akoelbl
 * @version 23.02.2016
 *
 */
public class Node implements Runnable{
	private TreeMap<Integer, Task> tasks;
	private StreamManager sm;
	
	public Node(Socket connection){
		sm = new StreamManager(connection);
		tasks = new TreeMap<Integer, Task>();
	}
	
	public void addTask(Task task){
		tasks.put(task.getSessId(), task);
		sm.write(task.getSessId() + ";" + task.getCmd());
	}
	
	public Collection<Task> getTasks(){
		return tasks.values();
	}
	
	@Override
	public String toString() {
		return "Node[StreamManager=" + sm + ",tasks=" + tasks + "]";
	}

	@Override
	public void run() {
		String msg = "";
		
		while((msg = sm.read()) != null){
			String[] teile = msg.split(";");
			int sessId = Integer.parseInt(teile[0]);
			if(tasks.containsKey(sessId)){
				Task t = tasks.get(sessId);
				t.setResult(teile[1]);
				t.setState(Task.FINISHED);
			}
		}
	}
}
