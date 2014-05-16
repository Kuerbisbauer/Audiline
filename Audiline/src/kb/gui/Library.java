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
		libraryBasics.fillLibrary(mtm, path);
	}


	private void guildGui() {
		setLayout(new BorderLayout());
		
		musicTable = new JTable(mtm);
		
		JScrollPane scrollPane = new JScrollPane();
		musicTable.setDragEnabled(true);
		
		scrollPane.setViewportView(musicTable);
		
		this.add(musicTable);
		this.add(scrollPane, BorderLayout.CENTER);
	}
}
