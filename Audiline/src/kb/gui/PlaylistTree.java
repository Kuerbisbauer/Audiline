package kb.gui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DropMode;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import kb.interfacs.MusicSelectionListener;
import kb.interfacs.SaveMusicListener;
import kb.jtreeOptions.JTreeMP3Renderer;
import kb.jtreeOptions.JTreeUtilities;
import kb.jtreeOptions.MusicTransfer;
import kb.library.MusicTableModel;
import kb.misc.MusicFiles;
import kb.misc.PlaylistWatch;
import kb.saveload.SaveLoad;

public class PlaylistTree extends JPanel implements MusicSelectionListener, SaveMusicListener{

	/*
	 * ######################################
	 * Konstante
	 * ######################################
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * ######################################
	 * GUI Komponenten
	 * ######################################
	 */
	private JTree tree ;
	
	/*
	 * ######################################
	 * Klassen Instanzen
	 * ######################################
	 */
	private JTreeUtilities jtreeUtilities = new JTreeUtilities();
	private PlaylistWatch playlistWatch;
	private SaveLoad saveLoad = new SaveLoad();


	/*
	 * ######################################
	 * Sonstige Attribute
	 * ######################################
	 */
	private DefaultMutableTreeNode root = new DefaultMutableTreeNode("New Playlist");


	/**
	 * Erstellt den JTree und die dazugehörigen Actionlistener
	 * und TransferHandler
	 * @param musicTableModel 
	 * @param jTable 
	 */
	public PlaylistTree(JTable jTable, MusicTableModel musicTableModel){
		buildPlaylistTree();
		addActionlistener();
		addJTreeBehavior(jTable, musicTableModel);
	}


	private void buildPlaylistTree() {
		MainWindow.lookAndFeelOS();
		playlistWatch = new PlaylistWatch();
		playlistWatch.setVisible(true);
		setLayout(new BorderLayout());
		
		/* 
		 * Reihenfolge des Codes einhalten, damit die 
		 * ScrollPane den Bereich ausfüllt.
		 */
		tree = new JTree(root);
		//newGroup();
		
		//ScrollPane, damit JTree scrollbar wird
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		//JTree wird angelegt und in die scrollPane gelegt
		scrollPane.setViewportView(tree);
		
		
	}
	
	private void addActionlistener() {
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				mouseBehavior(me);
			}
		});
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent arg0) {
				repaint();
			}
		});
	}
	
	protected void mouseBehavior(MouseEvent me) {
		if(me.getButton() == 1){
			jtreeUtilities.refreshPlaylist(tree);
			playlistWatch.refresh();
		}
	}

	/**
	 * Mit dieser Methode werden dem JTree Eigenschaften hinzugefügt.
	 * <li>Die Ordner und Dateien innerhalb des JTrees können umbenannt werden.
	 * <li>Drag'n'Drop innerhalb und außerhalb aktiviert
	 * <li>Gespielte Titel erhalten eine andere Farbe
	 * @param musicTableModel 
	 * @param jTable 
	 */
	private void addJTreeBehavior(JTable jTable, MusicTableModel musicTableModel) {
		//Leaves und Nodes können umbenannt werden
		tree.setEditable(true);
		
		//Daten können via Drag'n'Drop in den JTree gezogen werden
		tree.setDropMode(DropMode.ON_OR_INSERT);
		
		//Innerhalb des JTrees können via Drag'n'Drop Musiktitel verschoben werden
		tree.setDragEnabled(true);
		
		//Transferhandler um die Daten aus der Tabelle zu organisieren
		tree.setTransferHandler(new MusicTransfer(tree,jTable, musicTableModel));
		
		//Gespielte Titel werden im JTree grau hinterlegt
		tree.setCellRenderer(new JTreeMP3Renderer());
		
		//Die Wurzel des JTrees wird versteckt, da diese nicht direkt gebraucht wird
		//tree.setRootVisible(false);
	}

	@Override
	public void nextMusic() {
		jtreeUtilities.selectNext(tree, playlistWatch.getSelectedIndex());
	}

	@Override
	public void lastMusic() {
		jtreeUtilities.selectLast(tree, playlistWatch.getSelectedIndex());
	}
	
	@Override
	public void playMusic() {
		if(playlistWatch.getSelectedIndex() < 0)
			jtreeUtilities.selectNext(tree, playlistWatch.getSelectedIndex());
		
		jtreeUtilities.playMusic(tree, playlistWatch.getSelectedIndex());
	}
	
	
	public PlaylistWatch getPlaylistWatch() {
		return playlistWatch;
	}

	public void setPlaylistWatch(PlaylistWatch playlistWatch) {
		this.playlistWatch = playlistWatch;
	}


	@Override
	public void save() {
		saveLoad.save(tree);
	}


	@Override
	public void load() {
		saveLoad.load(tree);
	}
	
	public JTreeUtilities getJtreeUtilities() {
		return jtreeUtilities;
	}

	public void setJtreeUtilities(JTreeUtilities jtreeUtilities) {
		this.jtreeUtilities = jtreeUtilities;
	}
}
