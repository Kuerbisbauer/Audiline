package kb.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import kb.misc.PlaylistWatch;
import kb.thread.MusicProgress;

public class MainWindow extends JFrame{
	
	//TODO MusicPlayer onEndOfMedia
	
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
	
	private Library 		library 		= new Library();
	private Control			control			= new Control();
	private PlaylistTree	playlistTree	= new PlaylistTree(library.getMusicTable(), library.getMtm());
	private MusicProgress	musicProgress	= new MusicProgress();
	
	/*
	 * ######################################
	 * Sonstiges
	 * ######################################
	 */
	
	private JPanel	bottomControlBar = new JPanel();
	
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
		
		JScrollPane jsp = new JScrollPane();
		jsp.setViewportView(library);
		
		//Untere Kontrollleiste wird zusammengebaut
		bottomControlBar.setLayout(new BorderLayout());
		bottomControlBar.add(musicProgress, BorderLayout.NORTH);
		bottomControlBar.add(control, BorderLayout.CENTER);
		
		this.add(jsp, BorderLayout.CENTER);
		this.add(playlistTree, BorderLayout.EAST);
		this.add(bottomControlBar, BorderLayout.SOUTH);
		
		//Observer werden hinzugefügt
		//Reihenfolge beachten, da der Index von PlaylistWatch mit 0 beginnt -> Asynchron mit JTree 
		control.addMusicSelectionListener(playlistTree.getPlaylistWatch());
		control.addMusicSelectionListener(playlistTree);
		control.addSaveMusicListener(playlistTree);
		control.addMusicThreadListener(musicProgress);
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
			e.printStackTrace();
		}
	}
}
