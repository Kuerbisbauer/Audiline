package kb.jtreeOptions;


import kb.misc.*;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import kb.misc.PlaylistWatch;
import kb.musicPlayer.MusicToPlay;


public class JTreeNavigation
{	
	
	/**
	 * 	Der nächste Titel im JTree wird selektiert!
	 * 	Playlisten (Ordner) werden übersprungen
	 */
	public void selectNext(JTree tree, int index)
	{
		//Falls der Index kleiner als 0 ist, so wird dieser manuell auf 0 gesetzt
		if(index < 0)
			index = 0;
		
		//Der ausgewählte Zweig oder Blatt
		DefaultMutableTreeNode dmt = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
		
		//Wenn der ausgewählte Index im JTree ein Blatt ist, so wird der Status des Musiktitels auf
		//true gesetzt als Indikator dafür, dass dieser abgespielt wurde
		if(dmt.isLeaf())
			((MusicFiles) dmt.getUserObject()).setPlayed(true);
		
		//Der Index im JTree wird gesetzt
		dmt = MusicToPlay.getMusicToPlay().get(index);
		
		//Der Index wird angewandt
		tree.setSelectionPath(new TreePath(dmt.getPath()));
	}
}
