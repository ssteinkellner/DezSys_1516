package ak_ss.service;

/**
 * Created by Alex on 03.03.2016.
 */
public class PiService implements Service {
    public String serve(String command){
        String[] teile = command.split(";");
        int genauigkeit = Integer.parseInt(teile[1]);

        double pi = 0;
        int innerhalb = 0;
        int gesamt = genauigkeit;

        while (gesamt > 0) { // generiere Tropfen und addiere je nach Zugeh�rigkeit
            double dotx = Math.random();
            double doty = Math.random();

            if (dotx*dotx + doty*doty <= 1) {
                // Punkt liegt innerhalb des Kreises
                innerhalb++;
            } else {
                // Punkt liegt au�erhalb des Kreises
            }

            gesamt--;
        }

        pi = 4*(double)innerhalb/genauigkeit;
        return teile[0] + ";" + pi;
    }
}
