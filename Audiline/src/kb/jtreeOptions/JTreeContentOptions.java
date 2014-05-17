package kb.jtreeOptions;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;

import kb.misc.MusicFiles;
import kb.musicPlayer.MusicToPlay;



public class JTreeContentOptions{
	
	public JTreeContentOptions(){
		
	}
	
	/**
	 * Der ausgew�hlte Zweig oder Blatt wird nach Kindern untersucht und in eine Liste geschrieben.
	 * 
	 * @param tree 	- PlaylistTree
	 * @return		- Liste welche abgespielt werden soll
	 */
	public ArrayList<DefaultMutableTreeNode> getAllChildren(JTree tree){	
	
		ArrayList<DefaultMutableTreeNode> list = new ArrayList<DefaultMutableTreeNode>();
		
		//ausgew�hlter Zweig od. Blatt
		DefaultMutableTreeNode selectedElement = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
		
		//�berpr�fung ob Zweig oder Blatt
		if(selectedElement.isLeaf()){
			try{
				//MusicFiles m = new MusicFiles(((MusicFiles) selectedElement.getUserObject()).getM(), false);
				//Falls Blatt, dann wird nur dieses Element der Liste hinzugef�gt (ein einziger Titel zum Abspielen)
				list.add(selectedElement);
			}
			catch(Exception e){
				return null;
			}
		}
		else
			//Falls Zweig, dann wird jedes Blatt extrahiert und in die Liste gepackt
			getLeafFromNode(tree.getModel(), selectedElement, list);
		
		return list;
	}

	/**
	 * Durchsucht den ausgew�hlten Zweig nach Kindern(Bl�tter)
	 * 
	 * @param treeModel
	 * @param se
	 * @param list
	 */
	private void getLeafFromNode(TreeModel treeModel, DefaultMutableTreeNode se, List<DefaultMutableTreeNode> list){
		//Wieviele Kinder sich im Zweig befinden
		int count = treeModel.getChildCount(se);
		
		for(int i = 0; i < count; i++){
			DefaultMutableTreeNode child = (DefaultMutableTreeNode) treeModel.getChild(se, i);
			
			//�berpr�fung ob Zweig oder Blatt
			if(treeModel.isLeaf(child)){
				//MusicFiles m = new MusicFiles(((MusicFiles) child.getUserObject()).getM(), false);
				//Falls Blatt, dann wird nur dieses Element der Liste hinzugef�gt (ein einziger Titel zum Abspielen)
				list.add(child);
			}
			else
				//Falls Zweig, dann wird jedes Blatt extrahiert und in die Liste gepackt
				getLeafFromNode(treeModel, child, list);
		}
	}

	/**
	 * Musiktitel werden wieder zur�ckgesetzt. Im JTree werden diese wieder
	 * in schwarzer Farbe aufgelistet.
	 */
	public void refresh(){
		for(DefaultMutableTreeNode dmtn : MusicToPlay.getMusicToPlay())
			((MusicFiles) dmtn.getUserObject()).setPlayed(false);
	}
}
