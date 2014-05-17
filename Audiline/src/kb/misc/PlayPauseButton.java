package kb.misc;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;

public class PlayPauseButton extends JButton{

	private boolean clicked;
	
	public PlayPauseButton(){
		setClicked(false);
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
		
		if(isClicked())
			setText("Pause");
		else
			setText("Play");
	}

	public void changeClicked() {
		if(isClicked())
			setClicked(false);
		else
			setClicked(true);
	}
}
