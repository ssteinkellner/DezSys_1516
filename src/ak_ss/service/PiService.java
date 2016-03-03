package ak_ss.service;

/**
 * Created by Alex on 03.03.2016.
 */
public class PiService implements Service {
    public String serve(String command){
        int tropfenanzahl = Integer.parseInt(command);

        double pi = 0;
        int innerhalb = 0;
        int gesamt = tropfenanzahl;

        while (gesamt > 0) { // generiere Tropfen und addiere je nach Zugehörigkeit
            double dotx = Math.random();
            double doty = Math.random();

            if (dotx*dotx + doty*doty <= 1) {
                // Punkt liegt innerhalb des Kreises
                innerhalb++;
            } else {
                // Punkt liegt außerhalb des Kreises
            }

            gesamt--;
        }

        pi = 4*(double)innerhalb/tropfenanzahl;
        return ""+pi;
    }
}
