package kb.gui;

import javafx.embed.swing.JFXPanel;

import javax.swing.UnsupportedLookAndFeelException;

public class Start {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		//JavaFx wird initialisiert
		new JFXPanel();
		//MainWindow wird geladen und angezeigt
		MainWindow mw = new MainWindow();
		mw.setVisible(true);
	}
}
