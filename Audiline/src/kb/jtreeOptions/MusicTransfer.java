package kb.jtreeOptions;

import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import kb.entities.Music;

public class MusicTransfer extends TransferHandler
{

	private static final long	serialVersionUID	= 1L;
	private JTree tree;
	
	public MusicTransfer(JTree tree)
	{
		this.tree = tree; 
	}

	public boolean canImport(TransferHandler.TransferSupport info)
	{
		if(!info.isDrop())
			return false;
		
		info.setShowDropLocation(true);
		
		return true;
	}
	
	public boolean importData(TransferHandler.TransferSupport info)
	{
		if(!canImport(info))
			return false;
		
		JTree.DropLocation dl = (javax.swing.JTree.DropLocation) info.getDropLocation();
		TreePath path = dl.getPath();
			
		int childIndex = dl.getChildIndex();
		
		MusicTableModel mtm = Library.getMtm();
		JTable table = Library.getJTable();
		
		int i[] = table.getSelectedRows();
		Music m;
		MusicFiles mf;
		
		DefaultMutableTreeNode newNode = null;
		DefaultMutableTreeNode parentNode = null;
		
		for(int j : i)
		{
			if(childIndex == -1)
				childIndex = tree.getModel().getChildCount(path.getLastPathComponent());
			
			m = new Music();
			m.setAbsoultePath((String) mtm.getValueAt(j, 4));
			m.setTitle((String) mtm.getValueAt(j, 1));
			
			mf = new MusicFiles(m, false);
			
			newNode = new DefaultMutableTreeNode(mf);
			parentNode = (DefaultMutableTreeNode) path.getLastPathComponent();
			String s = parentNode.getUserObject().toString();
			try{
				MusicFiles mfn =  (MusicFiles) parentNode.getUserObject();
				s = mfn.getM().getTitle();
			}catch(Exception e)
			{}
			
			parentNode.setUserObject(s);
			
			parentNode.add(newNode);
			((DefaultTreeModel) tree.getModel()).insertNodeInto(newNode, parentNode, childIndex);
			childIndex++;
		}
		JTreeBasics.autoExpandJTree(tree);
		if(parentNode.getChildCount()>0)
			parentNode = new DefaultMutableTreeNode(path.getLastPathComponent().toString());
		
		return true;
	}
}
