package kb.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import kb.library.LibraryBasics;
import kb.library.MusicTableModel;
import kb.misc.FileSearch;

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
	private MusicTableModel mtm;
	private LibraryBasics libraryBasics = new LibraryBasics();
	
	
	public Library(){
		libraryBasics.fillDatabase(FileSearch.chooseDirectory());
		guildGui();
	}


	/**
	 * Baut den JTable zusammen und bef�llt diesen mit Daten
	 */
	private void guildGui() {
		setLayout(new BorderLayout());
		
		//Darf erst sp�ter instanziert werden, da zuerst die Datenbank bef�llt werden muss
		mtm  = new MusicTableModel();
		
		//Das Muster der Tabelle wird definiert und die Datens�tze werden hinzugef�gt
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
