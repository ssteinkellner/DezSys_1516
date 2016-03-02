package ak_ss.balancer;

import java.util.List;

public class AlgorithmFactory {
	
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
