package kb.saveload;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import kb.misc.FileSearch;

public class SaveLoad implements Serializable{

	private static final long	serialVersionUID	= 1L;
	private List<TreeNode[]> expanded;
	
	public void save(JTree tree) {
		TreeModel model = tree.getModel();
		expanded = new ArrayList<TreeNode[]>();
		getTreeInformation(model.getRoot(), model, tree);
		savePlaylist(model);
	}
	
	/**
	 * Die Playlist wird mithilfe von XML gespeichert.
	 * Der Pfad ist noch hardcoded.
	 * 
	 * @param model - TreeModel um die Pfade nachzuvollziehen
	 */
	private void savePlaylist(TreeModel model) {
		try{
			String path = FileSearch.chooseFile("Save");
			
			if(path != null){
				XMLEncoder xml = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path)));
				xml.writeObject(model);
				xml.writeObject(expanded);
				xml.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	/**
	 * Überprüft die gegebene Position im JTree ob Zweige vorhanden sind.
	 * Wenn ja, wird dieser in die TreeNode[] ArrayList eingetragen
	 * 
	 * @param poistionTree
	 * @param model	- TreeModel um die Pfade nachzuvollziehen
	 * @param tree	- Die Playlist
	 */
	private void getTreeInformation(Object poistionTree, TreeModel model, JTree tree){
		//Anzahl der Kinder im Zweig
		int childCount = model.getChildCount(poistionTree);
		
		//Für jedes Kind, das ein Zweig ist, gibt es einen Eintrag in die ArrayList
		for(int i = 0; i < childCount; i++){
			//Position im JTree
			DefaultMutableTreeNode dmt = (DefaultMutableTreeNode) model.getChild(poistionTree, i);
			//Der Pfad zum Kind
			TreeNode[] treeNode = dmt.getPath();
			
			//Wenn das Kind ein Zweig ist, so wird dieser auch nach möglichen Zweigen durchsucht
			if(!model.isLeaf(dmt) && tree.isExpanded(new TreePath(treeNode))){
				expanded.add(treeNode);
				getTreeInformation(dmt, model, tree);
			}
		}
	}

	/**
	 * Die gespeicherte Playlist wird geladen und alle Blätter
	 * auf den Zweigen im JTree werden angezeigt
	 * 
	 * @param tree
	 */
	public void load(JTree tree) {
		loadPlaylist(tree);
		loadTreeInformation(tree);
	}
	
	/**
	 * Öffnet jeden Zweig im Baum
	 * 
	 * @param tree
	 */
	private void loadTreeInformation(JTree tree){
		for(int i = expanded.size() - 1; i > -1; i--){
			TreeNode[] tn = expanded.get(i);
			tree.expandPath(new TreePath(tn));
		}
	}
	
	/**
	 * Die XML Datei wird geladen. Das TreeModel wird übernommen und geöffnet
	 * 
	 * @param jtree
	 */
	private void loadPlaylist(JTree jtree){
		try{
			String path = FileSearch.chooseFile("Save");
			
			if(path != null){
				XMLDecoder xml = new XMLDecoder(new BufferedInputStream(new FileInputStream(path)));
				jtree.setModel((TreeModel) xml.readObject());
				setExpanded((List<TreeNode[]>) xml.readObject());
				xml.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public List<TreeNode[]> getExpanded() {
		return expanded;
	}

	public void setExpanded(List<TreeNode[]> expanded) {
		this.expanded = expanded;
	}
}
