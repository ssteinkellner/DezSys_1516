package at.geyersteinkellner.dezsys08.view;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import at.geyersteinkellner.dezsys08.opencl.OpenCLSum;

/**
 * @author ssteinkellner
 * @version 20160110
 */
public class View extends JPanel implements ActionListener {
    private JButton select, start;
    private JComboBox<Character> mode;
    private JComboBox<String> unit;
    private NumberField runs, delta, tasks;
    private JLabel lruns, ldelta, ltasks, lunit;

    public View() {
        /* elemente erstellen */
        lruns = new JLabel("Runden");
        runs = new NumberField(10);
        
        ltasks = new JLabel("Anzahl Aufgaben");
        tasks = new NumberField(10);
        
        ldelta = new JLabel("Aufgabenanstieg");
        delta = new NumberField(10, true);
        mode = new JComboBox<Character>(new Character[]{'+', '*'});
        
        lunit = new JLabel("Processing Unit");
        unit = new JComboBox<String>(new String[]{"CPU","GPU"});

        start = new JButton("Start");
        select = new JButton("Speicherort waehlen");

        /* listener */
        select.addActionListener(this);
        start.addActionListener(this);
        
        /* margins */
        {
            Insets i = new Insets(0, 0, 0, 0);
            start.setMargin(i);
            select.setMargin(i);
        }
        
        /* positionierung */
        int y = 10, h = 25;
        {
            int x1 = 10, x2 = 120 , x3 = 160;
            int w1 = 110, w2 = 40, w3 = 60;

            lunit.setBounds(x1, y, w1, h);
            unit.setBounds( x3, y, w3, h - 1);

            y += h + 5;
            lruns.setBounds(x1, y, w1, h);
            runs.setBounds (x3, y, w3, h);

            y += h + 5;
            ltasks.setBounds(x1, y, w1, h);
            tasks.setBounds (x3, y, w3, h);

            y += h + 5;
            ldelta.setBounds(x1, y, w1, h);
            mode.setBounds(  x2, y, w2, h - 1);
            delta.setBounds( x3, y, w3, h);

            y += h + 5;
            select.setBounds(x1, y, w1 + w2, h);
            start.setBounds( x3, y, w3, h);
        }
        this.setPreferredSize(new Dimension(230, y + h + 10));
        
        /* anzeigen */
        this.setLayout(null);
        this.add(lunit);  this.add(unit);
        this.add(lruns);  this.add(runs);
        this.add(ltasks); this.add(tasks);
        this.add(ldelta); this.add(mode); this.add(delta);
        this.add(select); this.add(start);
    }

    public void actionPerformed(ActionEvent ae) {
        Object src = ae.getSource();

        if (src == select) {
            //tutorial: http://www.rgagnon.com/javadetails/java-0370.html

            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File(""));
            chooser.setDialogTitle("Speicherort Waehlen");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);

            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
                System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
            } else {
                System.out.println("No Selection");
            }
        } else if (src == start) {
            System.out.println(mode.getSelectedItem());
            
            int type = OpenCLSum.GPU;
            if(unit.getSelectedItem() == "CPU") type = OpenCLSum.CPU;

            try {
            	new OpenCLSum(type, runs.getNumber(), tasks.getNumber());
			} catch (Exception e) {
				System.err.println("Problem with OpenCLSum: " + e);
//				e.printStackTrace();
			}
        }
    }
}