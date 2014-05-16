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
	 * Falls der Bereich Drag'n'Drop unterst�tzt, so wird true zur�ckgeliefert
	 */
	public boolean canImport(TransferHandler.TransferSupport info){
		if(!info.isDrop())
			return false;
		
		info.setShowDropLocation(true);
		
		return true;
	}
	
	/**
	 * Die Daten aus der Tabelle werden verarbeitet und in den JTree als Bl�tter eingeschrieben.
	 * 
	 * Werden die transferierten Daten in eine leere Fl�che gelegt, so werden im root
	 * Verzeichnis die Bl�tter angelegt.
	 * 
	 */
	public boolean importData(TransferHandler.TransferSupport info){
		//Falls der Bereich kein Drag'n'Drop unterst�tz, so wird die Methode an dieser Stelle beendet und
		//falls zur�ckgegeben
		if(!canImport(info))
			return false;
		
		/*
		 * Der Bereich des Drops wird in 'dl' gespeichert.
		 * Entweder ein Ordner, Datei oder eine freie Fl�che.
		 */ 
		JTree.DropLocation dl = (javax.swing.JTree.DropLocation) info.getDropLocation();
		TreePath path = dl.getPath();
		
		//Die Position des Drops (welche Position im JTree)
		int childIndex = dl.getChildIndex();
		
		//MusicTableModel mtm = Library.getMtm();
		//JTable table = Library.getJTable();
		
		//Ausgew�hlte Zeilen im Library Table
		int i[] = table.getSelectedRows();
		Music m;
		MusicFiles mf;
		
		DefaultMutableTreeNode newNode = null;
		DefaultMutableTreeNode parentNode = null;
		
		//F�r jede ausgew�hlte Datei wird diese Schleife ausgef�hrt
		for(int j : i){
			//An die letzte Stelle der Playlist, falls kein Ordner oder Datei ausgew�hlt wurde
			if(childIndex == -1)
				childIndex = tree.getModel().getChildCount(path.getLastPathComponent());
			
			//Ein neues Musikobjekt wird erzeugt anhand der Daten im Table (Workarround)
			m = new Music();
			m.setAbsoultePath((String) musicTableModel.getValueAt(j, 4));
			m.setTitle((String) musicTableModel.getValueAt(j, 1));
			
			//F�r das neue Musikobjekt wird eine neues MusicFile erzeugt, welches
			//die Daten von Music enth�lt inkl die Eigenschaft ob diese abgespielt wurde oder nicht.
			mf = new MusicFiles(m, false);
			
			//Neuer Zweig
			newNode = new DefaultMutableTreeNode(mf);
			
			//Der Zweig der mit dem Cursor via Drag'n'Drop ausgew�hlt wurde
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
			
			//Dem Zweig wird ein neues Blatt hinzugef�gt
			parentNode.add(newNode);
			
			//Das Blatt wird im JTree an die jeweilige Position geh�ngt
			((DefaultTreeModel) tree.getModel()).insertNodeInto(newNode, parentNode, childIndex);
			
			//Die Position im JTree wird um 1 addiert
			childIndex++;
		}
		
		//Nachdem ein Blatt/Zweig hinzugef�gt wurde, wird dieser automatisch angezeigt
		JTreeUtilities.autoExpandJTree(tree);
		
		//Wenn im Zweig Kinder sind, so wird das neue Kind an die letzte Position im Zweig geh�ngt
		if(parentNode.getChildCount()>0)
			parentNode = new DefaultMutableTreeNode(path.getLastPathComponent().toString());
		
		return true;
	}
}
