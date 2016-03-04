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
        String[] teile = msg.split(";");
        if(teile.length < 2) return teile[0] + ";TaskException: Invalid Task";
        
        System.out.println("Berechne: "  + teile[1]);
        String res = s.serve(teile[1]);
        System.out.println("Ergebnis: " + res);
        
        return teile[0] + ";" + res;
    }
}
