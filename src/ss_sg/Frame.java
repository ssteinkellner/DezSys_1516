
/**
 * @autor ssteinkellner
 * @version 20160111
 */
public class Frame extends JFrame{
	private JPanel view;
	
	public Frame(JPanel view){
		this.setTitle("GPGPU");
		this.setSize(300,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.view = view;
		this.add(view);
		this.setVisible(true);
	}
	
	public replaceView(JPanel view){
		this.remove(this.view);
		
		this.view = view;
		this.add(view);
	}
}