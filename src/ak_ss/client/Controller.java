package ak_ss.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import ak_ss.common.Default;
import ak_ss.common.Tools;

/**
 * 
 * @author SSteinkellner, Akoelbl
 * @version 23.02.2016
 *
 */
public class Controller implements ActionListener {
	private Model m;
	private View v;
	private Socket conn;
	
	public Controller(){
		this(Default.HOST);
	}
	
	public Controller(String host){
		this(host, Default.PORT);
	}
	
	public Controller(String host, int port){
		m = new Model(host, port);
		v = new View(m, this);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String cmd = ae.getActionCommand().toLowerCase();
		
		if(cmd.equals(Model.CMD_CONNECT)){
			m.setState(Model.SEND);
			try {
				conn = new Socket(m.getHost(),m.getPort());
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			v.update();
		}else if(cmd.equals(Model.CMD_SEND)){
			m.setState(Model.CONNECT);
			v.update();
		}
	}

	public static void main(String[] args) {
		if(args.length == 0){
			new Controller();
			return;
		}
		
		if(args.length == 1){
			if(args[0].equalsIgnoreCase("help")){
				printUsage();
				return;
			}
			
			new Controller(args[0]);
			return;
		}
		
		if(args.length == 2){
			int port = Tools.isPort(args[1]);
			if(port == Tools.NOT_PORT){
				System.err.println("invalid portnumber!");
			}else if(port != Tools.NOT_NUMERIC){
				new Controller(args[0], Integer.parseInt(args[1]));
				return;
			}
		}
		
		printUsage();
	}
	
	public static void printUsage(){
		System.out.println("Usage: client [<host> [<port>]]");
	}
}
