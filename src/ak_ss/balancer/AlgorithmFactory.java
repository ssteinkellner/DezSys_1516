package ak_ss.balancer;

import java.util.List;

import ak_ss.balancer.algorithms.LeastConnection;
import ak_ss.balancer.algorithms.ResponseTime;

public class AlgorithmFactory {
	public static final String DEFAULT = "leastconnection";
	
	public static Algorithm loadAlgorithm(String name, List nodes){
		name = name.toLowerCase();
		
		if(name.equals("leastconnection")){
			return new LeastConnection(nodes);
		}else if(name.equals("responsetime")){
			return new ResponseTime(nodes);
		}
		
		return null;
	}
}
