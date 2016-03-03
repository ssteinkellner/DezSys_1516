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
        System.out.println("Berechne "  + teile[1]);
        return teile[0] + ";" + s.serve(teile[1]);
    }
}
