package ak_ss.balancer.algorithms;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import ak_ss.balancer.Algorithm;
import ak_ss.balancer.Node;
import ak_ss.balancer.Task;

/**
 * 
 * @author SSteinkellner, Akoelbl
 * @version 23.02.2016
 *
 */
public class ResponseTime implements Algorithm, Runnable {

	private Collection<Node> nodes;
	private Node fastest;
	private boolean lauf;

	public ResponseTime(List nodes){
		this.nodes = nodes;
		new Thread(this).start();
	}

	@Override
	public void run(){
		lauf = true;
		while (lauf) {
			if(nodes.size()>0){
				System.out.println("Checke Server");
				calculateFastest();
			}else{
				fastest = null;
			}
			try {
				Thread.sleep(10000);
			} catch (Exception e) {
				e.printStackTrace();
				lauf = false;
			}
		}
	}

	public void stop(){
		lauf = false;
	}

	public void calculateFastest(){
		Iterator<Node> i = nodes.iterator();
		RuntimeSave rs = new RuntimeSave();
		while (i.hasNext()) {
			Node n = i.next();
			Task test = new Task("1000000");
			test.onFinish(() -> {
				if(rs.setRuntime(test.getRunTime())){
					fastest = n;
				}
			});
			n.addTask(test);
		}
	}

	@Override
	public Node getNext() {
		return fastest;
	}

	private  class RuntimeSave{
		private long runtime = -1;
		public boolean setRuntime(long runtime){
			if(this.runtime < 0 || this.runtime > runtime){
				this.runtime = runtime;
				return true;
			}
			return false;
		}
		public long getRuntime(){ return runtime; }
	}
}
