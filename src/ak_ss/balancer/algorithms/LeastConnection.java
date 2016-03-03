package ak_ss.balancer.algorithms;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import ak_ss.balancer.Algorithm;
import ak_ss.balancer.Node;

/**
 * 
 * @author SSteinkellner, Akoelbl
 * @version 23.02.2016
 *
 */
public class LeastConnection implements Algorithm {

	private Collection<Node> nodes;
	
	public LeastConnection(List nodes){
		this.nodes = nodes;
	}
	
	@Override
	public Node getNext() {
		Iterator<Node> i = nodes.iterator();
		int min = -1;
		Node least = null;

		while(i.hasNext()) {
			Node n = i.next();
			int count = n.getTasks().size();
			if(count < min || min == -1){
				min = count;
				least = n;
			}
		}
		return least;
	}
	
}
