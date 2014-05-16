package kb.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Library extends JPanel{

	private JTable musicTable = new JTable();
	
	
	public Library(){
		guildGui();
	}


	private void guildGui() {
		setLayout(new BorderLayout());
		
		JScrollPane scrollPane = new JScrollPane();
		musicTable.setDragEnabled(true);
		
		scrollPane.setViewportView(musicTable);
		
		this.add(musicTable);
		this.add(scrollPane, BorderLayout.CENTER);
	}
}
