package kb.jtreeOptions;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import musicPlayer.MusicFiles;

public class JTreeMP3Renderer extends DefaultTreeCellRenderer
{

	/*
	 * ######################################
	 * Konstante
	 * ######################################
	 */
	private static final long	serialVersionUID	= 1L;
	
	//Farben für gespielt und nicht gespielt.
	private static final Color playedColor = Color.gray;
	private static final Color pendingColor = Color.black;

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus)
	{
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		
		
		DefaultMutableTreeNode dmt = (DefaultMutableTreeNode) value;
		MusicFiles mf = null;
		try{
			mf = (MusicFiles) dmt.getUserObject();
		}catch(Exception e)
		{}
		
		if(mf != null)
		{
			if(mf.isPlayed())
				setForeground(playedColor);
			else
				setForeground(pendingColor);	
		}
		
		return this;
		
	}
}
