package kb.jtreeOptions;

import javax.swing.JTree;

import kb.musicPlayer.MusicToPlay;

public class JTreeUtilities {

	private JTreeContentOptions jtco = new JTreeContentOptions();
	private JTreeNavigation jtreeNavigation = new JTreeNavigation();
	
	public JTreeUtilities(){
		
	}

	/**
	 * Die Playlist wird aktualisiert, abh�ngig davon welcher
	 * Zweig/Blatt ausgew�hlt wurde.
	 * 
	 * @param tree - Die aktuelle Playlist
	 */
	public void refreshPlaylist(JTree tree) {
		MusicToPlay.setMusicToPlay(jtco.getAllChildren(tree));
		tree.repaint();
	}
	

	/**
	 * Alle Bl�tter und Zweige des Baumes werden angezeigt
	 * 
	 * @param tree - Die aktuelle Playlist
	 */
	public static void autoExpandJTree(JTree tree) {
		for(int i=0;i<tree.getRowCount();i++)  {  
            tree.expandRow(i);  
        }  
	}
	
	/**
	 * Der n�chste Musiktitel wird ausgew�hlt
	 * 
	 * @param tree - Die aktuelle Playlist
	 * @param index - Der Index in der PlaylistWatch
	 */
	public void selectNext(JTree tree, int index){
		System.out.println("NEXT");
		jtreeNavigation.selectNext(tree, index);
	}

	/**
	 * Der vorige Musiktitel wird ausgew�hlt
	 * 
	 * @param tree	- Die aktuelle Playlist
	 * @param index - Der Index in der PlaylistWatch
	 */
	public void selectLast(JTree tree, int index) {
		System.out.println("LAST");
		jtreeNavigation.selectNext(tree, index);
	}
}
