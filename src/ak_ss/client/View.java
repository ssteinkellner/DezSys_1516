package ak_ss.client;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * 
 * @author SSteinkellner, Akoelbl
 * @version 23.02.2016
 *
 */
public class View extends JFrame implements KeyListener {
	private JTextField host;
	private NumberField input;
	private JLabel msg;
	private Model m;
	private JButton go;
	
	public View(Model model, ActionListener actionListener){
		this(model);
		addActionListener(actionListener);
	}
	
	public View(Model model){
		m = model;
		
		setTitle("Client | SSteinkellner, Akoelbl");
		setSize(500,130);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		host = new JTextField(m.getHost());
		input = new NumberField();
		msg = new JLabel();
		go = new JButton();
		
		host.addKeyListener(this);
		
		setLayout(new GridLayout(3,2));
		add(new JLabel("Server"));	add(host);
		add(new JLabel("Eingabe"));	add(input);
		add(msg);					add(go);
		
		update();
		setVisible(true);
	}
	
	public void addActionListener(ActionListener actionListener){
		go.addActionListener(actionListener);
	}
	
	public void update(){
		int state = m.getState();
		
		host.setEnabled(state == Model.CONNECT);
		input.setEnabled(state == Model.SEND);
		
		go.setText((state == Model.CONNECT)?"Verbinden":"Senden");
		go.setActionCommand((state == Model.CONNECT)?Model.CMD_CONNECT:Model.CMD_SEND);
		
		msg.setText(m.getLastMsg());
	}
	
	public String getCommand(){
		return input.getText();
	}

	@Override
	public void keyPressed(KeyEvent ke) { m.setHost(host.getText()); }

	@Override
	public void keyReleased(KeyEvent ke) { m.setHost(host.getText()); }

	@Override
	public void keyTyped(KeyEvent ke) { m.setHost(host.getText()); }
}
