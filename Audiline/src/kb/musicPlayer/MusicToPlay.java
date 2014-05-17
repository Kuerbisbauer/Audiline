package kb.musicPlayer;

import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

public class MusicToPlay
{
	private static ArrayList<DefaultMutableTreeNode> MusicToPlay = new ArrayList<DefaultMutableTreeNode>();
	
	public static ArrayList<DefaultMutableTreeNode> getMusicToPlay(){
		return MusicToPlay;
	}
	
	public static void setMusicToPlay(ArrayList<DefaultMutableTreeNode> list){
		MusicToPlay = list;
	}
}
