package kb.jtreeOptions;

import javafx.embed.swing.JFXPanel;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import kb.musicPlayer.MusicPlayer;
import kb.musicPlayer.MusicToPlay;

public class JTreeUtilities {

	private JTreeContentOptions jtco = new JTreeContentOptions();
	private JTreeNavigation jtreeNavigation = new JTreeNavigation();
	private MusicPlayer musicPlayer = new MusicPlayer();
	/**
	 * Die Playlist wird aktualisiert, abhängig davon welcher
	 * Zweig/Blatt ausgewählt wurde.
	 * 
	 * @param tree - Die aktuelle Playlist
	 */
	public void refreshPlaylist(JTree tree) {
		MusicToPlay.setMusicToPlay(jtco.getAllChildren(tree));
		tree.repaint();
		musicPlayer.initialize();
	}
	

	/**
	 * Alle Blätter und Zweige des Baumes werden angezeigt
	 * 
	 * @param tree - Die aktuelle Playlist
	 */
	public static void autoExpandJTree(JTree tree) {
		for(int i=0;i<tree.getRowCount();i++)  {  
            tree.expandRow(i);  
        }  
	}
	
	public void playMusic(JTree tree, int index){
		if(index == 0)
			jtreeNavigation.selectNext(tree, index);
		
		musicPlayer.play();
	}
	
	/**
	 * Der nächste Musiktitel wird ausgewählt.
	 *
	 * (Ausbaufähig) Ist man an der Ende der Playlist angekommen, so
	 * werden alle Titel auf unplayed gesetzt und wieder in schwarzer
	 * Farbe angezeigt
	 * 
	 * @param tree - Die aktuelle Playlist
	 * @param index - Der Index in der PlaylistWatch
	 */
	public void selectNext(JTree tree, int index){
		System.out.println("NEXT");
		jtreeNavigation.selectNext(tree, index);
		musicPlayer.next();
		
		//Setzt alle Titel zurück auf unplayed und werden somit
		//wieder in schwarzer Farbe dargestellt
		if(index == 0)
			jtco.refresh();
	}

	/**
	 * Der vorige Musiktitel wird ausgewählt
	 * 
	 * @param tree	- Die aktuelle Playlist
	 * @param index - Der Index in der PlaylistWatch
	 */
	public void selectLast(JTree tree, int index) {
		System.out.println("LAST");
		jtreeNavigation.selectNext(tree, index);
		musicPlayer.back();
		
		//Setzt alle Titel zurück auf unplayed und werden somit
		//wieder in schwarzer Farbe dargestellt
		if(index == 0)
			jtco.refresh();
	}
}
