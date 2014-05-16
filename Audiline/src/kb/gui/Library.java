package kb.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import kb.library.LibraryBasics;
import kb.library.MusicTableModel;

public class Library extends JPanel{

	/*
	 * ######################################
	 * Konstante
	 * ######################################
	 */
	private static String path = "c:\\Test\\";
	
	/*
	 * ######################################
	 * GUI Komponenten
	 * ######################################
	 */
	private JTable musicTable;
	
	/*
	 * ######################################
	 * Klassen Instanzen
	 * ######################################
	 */
	private MusicTableModel mtm = new MusicTableModel();
	private LibraryBasics libraryBasics = new LibraryBasics();
	
	/*
	 * ######################################
	 * Sonstige Attribute
	 * ######################################
	 */
	
	
	public Library(){
		guildGui();
		libraryBasics.fillDatabase(path);
	}


	/**
	 * Baut den JTable zusammen und befüllt diesen mit Daten
	 */
	private void guildGui() {
		setLayout(new BorderLayout());
		
		//Das Muster der Tabelle wird definiert und die Datensätze werden hinzugefügt
		musicTable = new JTable(mtm);
		
		JScrollPane scrollPane = new JScrollPane();
		musicTable.setDragEnabled(true);
		
		scrollPane.setViewportView(musicTable);
		
		this.add(scrollPane, BorderLayout.CENTER);
	}


	public JTable getMusicTable() {
		return musicTable;
	}

	public void setMusicTable(JTable musicTable) {
		this.musicTable = musicTable;
	}

	public MusicTableModel getMtm() {
		return mtm;
	}

	public void setMtm(MusicTableModel mtm) {
		this.mtm = mtm;
	}
}
