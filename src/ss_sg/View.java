
/**
 * @autor ssteinkellner
 * @version 20160110
 */
public class View extends JPanel implements ActionListener{
	private JButton start;
	private JComboBox mode;
	private NumberField runs, add;
	
	public View(){
		runs = new NumberField();
		start = new JButton("Start");
		mode = new JComboBox(new String[]{"+","*"});
		add = new NumberField();
		
		start.addActionListener(this);
		
		this.setLayout(new GridLayout(2,2));
		this.add(runs);
		this.add(start);
		this.add(mode);
		this.add(add);
	}
	
	@Override
	public void ActionPerformed(ActionEvent ae){
		Object src = ae.getSource();
		
		if(src == start){
			//tutorial: http://www.rgagnon.com/javadetails/java-0370.html
			
			chooser = new JFileChooser(); 
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
		}
	}
}