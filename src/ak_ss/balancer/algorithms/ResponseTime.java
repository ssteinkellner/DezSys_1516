package ak_ss.balancer.algorithms;

import java.util.Collection;
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
public class ResponseTime implements Algorithm {

	private Collection<Node> nodes;
	private Node fastest;

	public ResponseTime(List nodes){
		this.nodes = nodes;
	}
	
	@Override
	public Node getNext() {
		Task test = new Task("{\"options\":{}}");
		return null;
	}
	
}
