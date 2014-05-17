package kb.jtreeOptions;

import javafx.embed.swing.JFXPanel;

import javax.swing.JTree;

import kb.musicPlayer.MusicPlayer;
import kb.musicPlayer.MusicToPlay;

public class JTreeUtilities {

	private JTreeContentOptions jtco = new JTreeContentOptions();
	private JTreeNavigation jtreeNavigation = new JTreeNavigation();
	private MusicPlayer musicPlayer = new MusicPlayer();
	
	public JTreeUtilities(){
		new JFXPanel();
	}

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
	 * Der nächste Musiktitel wird ausgewählt
	 * 
	 * @param tree - Die aktuelle Playlist
	 * @param index - Der Index in der PlaylistWatch
	 */
	public void selectNext(JTree tree, int index){
		System.out.println("NEXT");
		jtreeNavigation.selectNext(tree, index);
		musicPlayer.next();
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
	}
}
