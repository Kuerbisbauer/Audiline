package kb.jtreeOptions;


import kb.misc.*;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import kb.misc.PlaylistWatch;
import kb.musicPlayer.MusicToPlay;


public class JTreeNavigation
{	
	
	/*
	 * 	Der nächste Titel im JTree wird selektiert!
	 * 	Playlisten (Ordner) werden übersprungen
	 */

	public void selectNext(JTree tree, int index)
	{
		//PlaylistWatchNavigation.selectNext();
		getNext(tree, index);
	}
	
	public void selectPrevious()
	{
		//PlaylistWatchNavigation.selectPrevious();
		//getNext();
	}

	private void getNext(JTree tree, int index)
	{
		//JTree t = Playlist.getJTree();
		//int i = PlaylistWatch.getIndexOfSelectedTitle();
		
		if(index < 0)
			index = 0;
		
		DefaultMutableTreeNode dmt = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
		
		if(dmt.isLeaf())
			((MusicFiles) dmt.getUserObject()).setPlayed(true);
		
		dmt = MusicToPlay.getMusicToPlay().get(index);
		tree.setSelectionPath(new TreePath(dmt.getPath()));
		
		//if(PlaylistWatch.getIndexOfSelectedTitle() == 0)
			//new JTreeContentOptions().refresh();
	}
	
	
	/*

	public static void selectPrevious()
	{
		
		JTree tree = Playlist.getJTree();
		DefaultMutableTreeNode dmt = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
		DefaultMutableTreeNode first;
		
		if(dmt.getChildCount()>0)
			origin = dmt;
			
		first = origin.getFirstLeaf();
		
		if(first.equals(dmt))
			dmt = origin.getLastLeaf();
		else
			dmt = dmt.getPreviousNode();
		
		if(dmt.getChildCount()>0)
			dmt = dmt.getPreviousNode();
			
		TreePath tp = new TreePath(dmt.getPath());
		tree.setSelectionPath(tp);
		
		PlaylistWatchNavigation.selectPrevious();
		
	}
	*/
	
	/*
	 * 	Falls die nachfolgende Komponente eine Playlist (Ordner) ist,
	 * 	so wird diese von der Selektion ausgenommen und der erste Titel
	 * 	in der Playlist wird selektiert.
	 */

	/*
	private static void goDeeper(JTree tree, TreePath tp)
	{
		DefaultMutableTreeNode dmt = getLast();
		dmt = dmt.getNextNode();
		if(dmt.isLeaf())
		{
			tp = new TreePath(dmt.getPath());
			tree.setSelectionPath(tp);
		}
		else
		{
			setLast(dmt);
			goDeeper(tree, tp);
		}
	}*/
}
