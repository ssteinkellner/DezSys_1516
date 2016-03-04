package ak_ss.service;

/**
 * 
 * @author SSteinkellner, Akoelbl
 * @version 23.02.2016
 *
 */
public class Task {
    private Service s;

    public Task(){
        s = new PiService();
    }
    
	public String pi(String msg){
		if(msg.contains(";")){
			String[] teile = msg.split(";");
	        if(teile.length < 2) return teile[0] + ";TaskException: Invalid Task";
	        
	        return teile[0] + ";" + part(teile[1]);
		}
        
		return part(msg);
    }
	
	private String part(String cmd){
        System.out.println("Berechne: "  + cmd);
        String res = s.serve(cmd);
        System.out.println("Ergebnis: " + res);
        
        return res;
	}
}
