package at.geyersteinkellner.dezsys08.view;

import javax.swing.*;

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
//        this.setSize(250, 130);
        this.pack();
        this.setResizable(false);

        this.view = view;
        this.add(view);
        this.setVisible(true);
    }

    public void replaceView(JPanel view) {
        this.remove(this.view);

        this.view = view;
        this.add(view);
    }
}