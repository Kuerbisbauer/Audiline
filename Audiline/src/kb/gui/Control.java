package kb.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import kb.interfacs.MusicSelectionListener;
import kb.misc.PlayPauseButton;
import kb.misc.PlaylistWatch;

public class Control extends JPanel{

	/*
	 * ######################################
	 * GUI Komponenten
	 * ######################################
	 */
	private PlayPauseButton play_pause;
	private JButton next;
	private JButton back;
	
	/*
	 * ######################################
	 * Sonstige Attribute
	 * ######################################
	 */
	private static List<MusicSelectionListener> list = new ArrayList<MusicSelectionListener>();
	
	public Control(){
		buildGui();
		addActionListener();
	}

	public void addObserver(PlaylistTree playlistTree) {
		if(playlistTree != null)
			list.add(playlistTree);
	}
	
	public void addObserver(PlaylistWatch playlistWatch) {
		if(playlistWatch != null)
			list.add(playlistWatch);
	}

	private void buildGui() {
		setLayout(new FlowLayout());
		
		play_pause = new PlayPauseButton();
		next = new JButton("Next");
		back = new JButton("Back");
		
		this.add(back);
		this.add(play_pause);
		this.add(next);
	}

	private void addActionListener() {
		play_pause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				play_pause.changeClicked();
				for(MusicSelectionListener msl : list)
					msl.playMusic();
			}
		});
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(MusicSelectionListener msl : list)
					msl.lastMusic();
			}
		});
		
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(MusicSelectionListener msl : list)
					msl.nextMusic();
			}
		});	
	}

	
}
