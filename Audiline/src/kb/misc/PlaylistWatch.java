package kb.misc;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;

import kb.interfacs.MusicSelectionListener;
import kb.musicPlayer.MusicToPlay;

/**
 * Hilfsklasse um zu erfahren welche Titel abgespielt werden
 * 
 * @author Martin
 *
 */
public class PlaylistWatch extends JFrame implements MusicSelectionListener{

	/*
	 * ######################################
	 * Konstante
	 * ######################################
	 */
	private static final int posX = 900;
	private static final int posY = 20;
	
	private static final int width 	= 400;
	private static final int height = 800;
	
	/*
	 * ######################################
	 * GUI Komponenten
	 * ######################################
	 */
	private JList<String> list;
	
	/*
	 * ######################################
	 * Sonstige Attribute
	 * ######################################
	 */
	private DefaultListModel<String> dlm = new DefaultListModel<String>();
	
	public PlaylistWatch(){
		buildGui();
	
	}

	private void buildGui() {
		setLocation(posX, posY);
		setSize(width, height);
		
		list = new JList<String>(dlm);
		
		JScrollPane jsp = new JScrollPane();
		jsp.setViewportView(list);
		
		this.add(jsp, BorderLayout.CENTER);
	}
	
	/**
	 * Die Musikliste wird gelöscht und neu angelegt
	 */
	public void refresh(){
		dlm.clear();
		for(DefaultMutableTreeNode dmtn : MusicToPlay.getMusicToPlay()){
			dlm.addElement(((MusicFiles) dmtn.getUserObject()).getM().getTitle());
		}
	}

	/**
	 * @see kb.interfacs.MusicSelectionListener#nextMusic()
	 */
	@Override
	public void nextMusic() {
		int selectedIndex = list.getSelectedIndex();
		int lastIndex = list.getLastVisibleIndex();
		
		if(selectedIndex == -1)
			list.setSelectedIndex(0);
		else{
			if(selectedIndex < lastIndex)
				list.setSelectedIndex(selectedIndex+1);
			else
				list.setSelectedIndex(0);
		}
	}

	/**
	 * @see kb.interfacs.MusicSelectionListener#lastMusic()
	 */
	@Override
	public void lastMusic() {
		int selectedIndex = list.getSelectedIndex();
		int firstIndex = list.getFirstVisibleIndex();
		int lastIndex = list.getLastVisibleIndex();
		
		if(selectedIndex == -1)
			list.setSelectedIndex(lastIndex);
		else{
			if(selectedIndex > firstIndex)
				list.setSelectedIndex(selectedIndex-1);
			else
				list.setSelectedIndex(lastIndex);
		}
	}

	public int getSelectedIndex() {
		return list.getSelectedIndex();
	}

	/**
	 * @see kb.interfacs.MusicSelectionListener#playMusic()
	 */
	@Override
	public void playMusic() {
		int selectedIndex = list.getSelectedIndex();
		
		if(selectedIndex == -1)
			nextMusic();
	}
}
