
/**
 * @autor ssteinkellner
 * @version 20160110
 */
public class NumberField extends JTextField implements KeyListener{
	public NumberField(){
		this.addKeyListener(this);
	}

	/**
	 * laesst nur zahlen zu. dadurch wird das textfeld zu einem reinen zahlenfeld
	 */
	@Override
	public void keyPressed(KeyEvent ke) {
		int keyCode = ke.getKeyCode();
		System.out.println(keyCode);
		if (keyCode < 47 || keyCode > 58) ke.consume();
	}
	public void keyReleased(KeyEvent ke) {}
	public void keyTyped(KeyEvent ke) {}
}