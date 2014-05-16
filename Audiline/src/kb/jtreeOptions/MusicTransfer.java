package kb.jtreeOptions;

import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import kb.entities.Music;
import kb.library.MusicTableModel;
import kb.misc.MusicFiles;

public class MusicTransfer extends TransferHandler
{

	private static final long	serialVersionUID	= 1L;
	private JTree tree;
	private JTable table;
	private MusicTableModel musicTableModel;
	
	public MusicTransfer(JTree tree, JTable jTable, MusicTableModel musicTableModel){
		this.tree = tree; 
		this.table = jTable;
		this.musicTableModel = musicTableModel;
	}

	/**
	 * Falls der Bereich Drag'n'Drop unterstützt, so wird true zurückgeliefert
	 */
	public boolean canImport(TransferHandler.TransferSupport info){
		if(!info.isDrop())
			return false;
		
		info.setShowDropLocation(true);
		
		return true;
	}
	
	/**
	 * Die Daten aus der Tabelle werden verarbeitet und in den JTree als Blätter eingeschrieben.
	 * 
	 * Werden die transferierten Daten in eine leere Fläche gelegt, so werden im root
	 * Verzeichnis die Blätter angelegt.
	 * 
	 */
	public boolean importData(TransferHandler.TransferSupport info){
		//Falls der Bereich kein Drag'n'Drop unterstütz, so wird die Methode an dieser Stelle beendet und
		//falls zurückgegeben
		if(!canImport(info))
			return false;
		
		/*
		 * Der Bereich des Drops wird in 'dl' gespeichert.
		 * Entweder ein Ordner, Datei oder eine freie Fläche.
		 */ 
		JTree.DropLocation dl = (javax.swing.JTree.DropLocation) info.getDropLocation();
		TreePath path = dl.getPath();
		
		//Die Position des Drops (welche Position im JTree)
		int childIndex = dl.getChildIndex();
		
		//MusicTableModel mtm = Library.getMtm();
		//JTable table = Library.getJTable();
		
		//Ausgewählte Zeilen im Library Table
		int i[] = table.getSelectedRows();
		Music m;
		MusicFiles mf;
		
		DefaultMutableTreeNode newNode = null;
		DefaultMutableTreeNode parentNode = null;
		
		//Für jede ausgewählte Datei wird diese Schleife ausgeführt
		for(int j : i){
			//An die letzte Stelle der Playlist, falls kein Ordner oder Datei ausgewählt wurde
			if(childIndex == -1)
				childIndex = tree.getModel().getChildCount(path.getLastPathComponent());
			
			//Ein neues Musikobjekt wird erzeugt anhand der Daten im Table (Workarround)
			m = new Music();
			m.setAbsoultePath((String) musicTableModel.getValueAt(j, 4));
			m.setTitle((String) musicTableModel.getValueAt(j, 1));
			
			//Für das neue Musikobjekt wird eine neues MusicFile erzeugt, welches
			//die Daten von Music enthält inkl die Eigenschaft ob diese abgespielt wurde oder nicht.
			mf = new MusicFiles(m, false);
			
			//Neuer Zweig
			newNode = new DefaultMutableTreeNode(mf);
			
			//Der Zweig der mit dem Cursor via Drag'n'Drop ausgewählt wurde
			parentNode = (DefaultMutableTreeNode) path.getLastPathComponent();
			
			//Der Name des parentNodes
			String s = parentNode.getUserObject().toString();
					
			/*
			try{
				MusicFiles mfn =  (MusicFiles) parentNode.getUserObject();
				s = mfn.getM().getTitle();
			}catch(Exception e){e.printStackTrace();}
			*/
			
			parentNode.setUserObject(s);
			
			//Dem Zweig wird ein neues Blatt hinzugefügt
			parentNode.add(newNode);
			
			//Das Blatt wird im JTree an die jeweilige Position gehängt
			((DefaultTreeModel) tree.getModel()).insertNodeInto(newNode, parentNode, childIndex);
			
			//Die Position im JTree wird um 1 addiert
			childIndex++;
		}
		
		//Nachdem ein Blatt/Zweig hinzugefügt wurde, wird dieser automatisch angezeigt
		JTreeUtilities.autoExpandJTree(tree);
		
		//Wenn im Zweig Kinder sind, so wird das neue Kind an die letzte Position im Zweig gehängt
		if(parentNode.getChildCount()>0)
			parentNode = new DefaultMutableTreeNode(path.getLastPathComponent().toString());
		
		return true;
	}
}
