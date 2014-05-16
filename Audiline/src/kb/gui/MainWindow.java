package kb.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	
	/*
	 * ######################################
	 * Konstante
	 * ######################################
	 */
	
	/**
	 * Größe des Fensters
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
	
	private Library 		musicList 		= new Library();
	private Control			control			= new Control();
	private PlaylistTree	playlistTree	= new PlaylistTree();
	
	/**
	 * 	Kunstruktor legt die Größe des Fensters fest.<p>
	 * 	Das Aussehen des Fensters wird an das OS
	 * 	angepasst.
	 * 
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public MainWindow() {
		
		//Der Prozess wird beendet wenn das Programm über das geschlossen wird
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Position und Größe des Fensters
		setBounds(POSX, POSY, WIDTH, HEIGTH);
		
		setLayout(new BorderLayout());
		
		buildGui();
	}

	/**
	 * Setzt die GUI-Module in die gewünschte Positionen.
	 * 
	 * musicList 	- zentriert
	 * playlistTree	- rechtsgebunden
	 * control		- unterer Bereich
	 */
	private void buildGui() {
		lookAndFeelOS();
		
		playlistTree.setPreferredSize(new Dimension(200, 0));
		
		this.add(musicList, BorderLayout.CENTER);
		this.add(playlistTree, BorderLayout.EAST);
		this.add(control, BorderLayout.SOUTH);
	}
	
	/**
	 * Passt das Design an das verwendete OS an.
	 * 
	 * Static - Damit jede Klasse darauf Zugriff hat.
	 */
	public static void lookAndFeelOS(){
		//Aussehen wird an das OS angepasst
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
