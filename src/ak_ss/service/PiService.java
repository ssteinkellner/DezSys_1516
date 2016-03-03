package ak_ss.service;

import java.io.IOException;

/**
 * Created by Alex on 03.03.2016.
 */
public class PiService implements Service {
    public String serve(String command){
    	int tropfenanzahl;
    	
    	try{
            tropfenanzahl = Integer.parseInt(command);
    	}catch(NumberFormatException nfe){
    		System.err.println(nfe);
    		return "" + nfe;
    	}

        double pi = 0, dotx, doty;
        int innerhalb = 0;

        for(int gesamt = tropfenanzahl; gesamt > 0; gesamt--){ // generiere Tropfen und addiere je nach Zugehörigkeit
            dotx = Math.random();
            doty = Math.random();

            if (dotx*dotx + doty*doty <= 1) innerhalb++; // Punkt liegt innerhalb des Kreises
            // else Punkt liegt außerhalb des Kreises

 //           if(gesamt % 10000000 == 0) System.out.print("Nur noch " + gesamt + " Durchlaeufe!\r");
        }

        pi = 4*(double)innerhalb/tropfenanzahl;
        return ""+pi;
    }
}
