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
		select = new JButton("Speicherort w�hlen");
		
		/* listener */
		select.addActionListener(this);
		start.addActionListener(this);
		
		/* positionierung */
		{
			int x1=10, x2=160;
			int y1=10, y2=40, y3=70;
			int w1=110, w2=70;
			int h=25;
			
			lruns.setBounds(x1,y1,w1,h);
			runs.setBounds(x2,y1,w2,h);
			
			ldelta.setBounds(x1,y2,w1,h);
			mode.setBounds(x1+w1,y2,x2-x1-w1,h-1);
			delta.setBounds(x2,y2,w2,h);

			select.setBounds(x1,y3,150,h);
			start.setBounds(x2,y3,w2,h);
		}
		
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