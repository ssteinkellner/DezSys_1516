package ak_ss.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @author SSteinkellner, Akoelbl
 * @version 23.02.2016
 *
 */
public class Controller implements ActionListener {
	private Model m;
	private View v;
	
	public Controller(){
		m = new Model();
		v = new View(m, this);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String cmd = ae.getActionCommand().toLowerCase();
		
		if(cmd.equals("go")){
			m.setState(Model.SEND);
			v.update();
		}else if(cmd.equals("send")){
			m.setState(Model.CONNECT);
			v.update();
		}
	}

	public static void main(String[] args) {
		new Controller();
	}
}
