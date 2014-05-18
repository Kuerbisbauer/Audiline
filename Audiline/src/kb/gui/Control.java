package kb.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import kb.interfacs.MusicSelectionListener;
import kb.interfacs.SaveMusicListener;
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
	private JButton save;
	private JButton load;

	
	/*
	 * ######################################
	 * Listener
	 * ######################################
	 */
	private List<MusicSelectionListener> 	musicSelectionListener 	= new ArrayList<MusicSelectionListener>();
	private List<SaveMusicListener> 		saveMusicListener 		= new ArrayList<SaveMusicListener>(); 
	
	
	public Control(){
		buildGui();
		addActionListener();
	}

	public void addMusicListObserver(PlaylistTree playlistTree) {
		if(playlistTree != null)
			musicSelectionListener.add(playlistTree);
	}
	
	public void addMusicListObserver(PlaylistWatch playlistWatch) {
		if(playlistWatch != null)
			musicSelectionListener.add(playlistWatch);
	}
	
	public void addSaveMusicListObserver(PlaylistTree playlistTree){
		if(playlistTree != null)
			saveMusicListener.add(playlistTree);
	}

	private void buildGui() {
		setLayout(new FlowLayout());
		
		play_pause = new PlayPauseButton();
		next = new JButton("Next");
		back = new JButton("Back");
		save = new JButton("Save");
		load = new JButton("Load");
		
		this.add(back);
		this.add(play_pause);
		this.add(next);
		this.add(save);
		this.add(load);
	}

	private void addActionListener() {
		play_pause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				play_pause.changeClicked();
				for(MusicSelectionListener msl : musicSelectionListener)
					msl.playMusic();
			}
		});
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(MusicSelectionListener msl : musicSelectionListener)
					msl.lastMusic();
			}
		});
		
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(MusicSelectionListener msl : musicSelectionListener)
					msl.nextMusic();
			}
		});	
		
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(SaveMusicListener sml : saveMusicListener)
					sml.save();
			}
		});
		
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(SaveMusicListener sml : saveMusicListener)
					sml.load();
			}
		});
	}

	
}
