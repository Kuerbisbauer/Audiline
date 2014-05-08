package kb.gui;

import javax.swing.JFrame;

public class MainWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Gr��e des Fensters
	 * wird im Konstruker verwendet
	 */
	private static final int WIDTH 		= 800;
	private static final int HEIGTH 	= 800;
	
	
	/**
	 * 	Kunstruktor legt die Gr��e des Fensters fest
	 */
	public MainWindow(){
		setBounds(100, 20, WIDTH, HEIGTH);
	}
}
