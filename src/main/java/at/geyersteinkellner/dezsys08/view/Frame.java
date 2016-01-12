package at.geyersteinkellner.dezsys08.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @version 20160111
 * @author ssteinkellner
 */
public class Frame extends JFrame {
    private JPanel view;

    public Frame(JPanel view) {
        this.setTitle("GPGPU");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLocation(100, 100);
        this.setMinimumSize(new Dimension(200,100));
        this.setResizable(false);

        this.view = view;
        this.add(view);
        
        this.setVisible(true);
        this.pack();
    }

    public void replaceView(JPanel view) {
        this.remove(this.view);

        this.view = view;
        this.add(view);
    }
}