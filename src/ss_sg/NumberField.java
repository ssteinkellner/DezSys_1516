package ss_sg;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

/**
 * @autor ssteinkellner
 * @version 20160111
 */
public class NumberField extends JTextField implements KeyListener{
	private boolean consumeKey, allowNegative;

	public NumberField(){
		this(false);
	}
	public NumberField(boolean allowNegative){
		this.allowNegative = allowNegative;
		
		consumeKey = false;
		this.addKeyListener(this);
	}
	public NumberField(int Default){
		this(false);
		this.setText("" + Default);
	}
	public NumberField(int Default, boolean allowNegative){
		this(allowNegative);
		this.setText("" + Default);
	}

	/**
	 * laesst nur zahlen zu. dadurch wird das textfeld zu einem reinen zahlenfeld
	 */
	@Override
	public void keyPressed(KeyEvent ke) {
		int keyCode = ke.getKeyCode();
		if(keyCode ==  8 || keyCode == 127 || keyCode == 37 || keyCode ==  39) return;
		// erlaubt: backspace, entf, left, right
		
		char keyChar = ke.getKeyChar();
		if("0123456789".contains("" + keyChar)) return;
		if(allowNegative && keyChar == '-' && this.getCaretPosition() == 0 &&
				!this.getText().contains("-")) return;
		
		consumeKey = true;
		ke.consume();
	}
	public void keyReleased(KeyEvent ke) {
		if (consumeKey) ke.consume();
    }
	public void keyTyped(KeyEvent ke) {
		if (consumeKey) ke.consume();
        consumeKey = false;
	}
}