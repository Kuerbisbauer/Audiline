package kb.gui;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * ######################################
	 * Konstante
	 * ######################################
	 */
	
	/**
	 * Gr��e des Fensters
	 * wird im Konstruker verwendet
	 */
	private static final int WIDTH 		= 800;
	private static final int HEIGTH 	= 800;
	
	/**
	 * Position des Fensters auf der X und Y Achse
	 */
	private static final int POSX 	= 100;
	private static final int POSY	= 20;
	
	
	/*
	 * ######################################
	 * Klassen
	 * ######################################
	 */
	
	private MusicList 		musicList 		= new MusicList();
	private Control			control			= new Control();
	private PlaylistTree	playlistTree	= new PlaylistTree();
	
	/**
	 * 	Kunstruktor legt die Gr��e des Fensters fest.<p>
	 * 	Das Aussehen des Fensters wird an das OS
	 * 	angepasst.
	 * 
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public MainWindow() 
			throws 
				ClassNotFoundException, 
				InstantiationException, 
				IllegalAccessException, 
				UnsupportedLookAndFeelException{
		
		//Der Prozess wird beendet wenn das Programm �ber das geschlossen wird
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Aussehen wird an das OS angepasst
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		//Position und Gr��e des Fensters
		setBounds(POSX, POSY, WIDTH, HEIGTH);
	}
}
