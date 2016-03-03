package ak_ss.common;

public class Tools {
	public static final int NOT_NUMERIC = -1, NOT_PORT = -2;
	
	public static int isPort(String text){
		int port = NOT_NUMERIC;
		
		if(text.matches("[0-9]*")){
			int temp = Integer.parseInt(text);
			if(temp >= 0 && temp <= 65535){
				port = temp;
			}else{
				port = NOT_PORT;
			}
		}
		
		return port;
	}
}
