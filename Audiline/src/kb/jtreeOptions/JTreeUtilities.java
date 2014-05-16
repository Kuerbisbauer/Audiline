package kb.jtreeOptions;

import javax.swing.JTree;
import javax.swing.tree.*;

public class JTreeUtilities {

	public JTreeUtilities(){
		
	}

	public void refreshPlaylist() {
		
	}
	

	public static void autoExpandJTree(JTree tree) {
		for(int i=0;i<tree.getRowCount();i++)  
        {  
            tree.expandRow(i);  
        }  
	}
}
