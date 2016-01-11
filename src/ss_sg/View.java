package ss_sg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @autor ssteinkellner
 * @version 20160110
 */
public class View extends JPanel implements ActionListener{
	private JButton select, start;
	private JComboBox<Character> mode;
	private NumberField runs, delta;
	private JLabel ldelta, lruns;
	
	public View(){
		/* elemente erstellen */
		lruns = new JLabel("Runden");
		runs = new NumberField(10);
		
		ldelta = new JLabel("Durchlaufanstieg");
		delta = new NumberField(10,true);
		mode = new JComboBox(new Character[]{'+','*'});

		start = new JButton("Start");
		select = new JButton("Speicherort wählen");
		
		/* listener */
		select.addActionListener(this);
		start.addActionListener(this);
		
		/* positionierung */
		lruns.setBounds(10,10,100,25);
		runs.setBounds(160,10,70,25);
		
		ldelta.setBounds(10,40,100,25);
		mode.setBounds(120,40,40,24);
		delta.setBounds(160,40,70,25);

		select.setBounds(10,70,150,25);
		start.setBounds(160,70,70,25);
		
		/* anzeigen */
		this.setLayout(null);
		this.add(lruns);
		this.add(runs);
		this.add(ldelta);
		this.add(mode);
		this.add(delta);
		this.add(select);
		this.add(start);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae){
		Object src = ae.getSource();
		
		if(src == select){
			//tutorial: http://www.rgagnon.com/javadetails/java-0370.html
			
			JFileChooser chooser = new JFileChooser(); 
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setDialogTitle("Speicherort Waehlen");
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);
			
			if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
				System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
				System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
			} else {
				System.out.println("No Selection");
			}
		}else if(src == start){
			System.out.println(mode.getSelectedItem());
		}
	}
	
	public static void main(String[] args){
		new Frame(new View());
	}
}