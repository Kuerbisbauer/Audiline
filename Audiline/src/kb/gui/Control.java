package kb.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import kb.interfacs.MusicSelectionListener;
import kb.interfacs.MusicThreadListener;
import kb.interfacs.SaveMusicListener;
import kb.misc.PlayPauseButton;
import kb.misc.PlaylistWatch;
import kb.thread.MusicProgress;

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
	//Static Workaround
	private static List<MusicSelectionListener> 	musicSelectionListener 	= new ArrayList<MusicSelectionListener>();
	

	private List<SaveMusicListener> 				saveMusicListener 		= new ArrayList<SaveMusicListener>(); 
	private List<MusicThreadListener>				musicThreadListener		= new ArrayList<MusicThreadListener>();
	
	public Control(){
		buildGui();
		addActionListener();
	}

	public void addMusicSelectionListener(PlaylistTree playlistTree) {
		if(playlistTree != null)
			musicSelectionListener.add(playlistTree);
	}
	
	public void addMusicSelectionListener(PlaylistWatch playlistWatch) {
		if(playlistWatch != null)
			musicSelectionListener.add(playlistWatch);
	}
	
	public void addSaveMusicListener(PlaylistTree playlistTree){
		if(playlistTree != null)
			saveMusicListener.add(playlistTree);
	}
	
	public void addMusicThreadListener(MusicProgress musicProgress){
		if(musicProgress != null){
			musicThreadListener.add(musicProgress);
		}
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
				
				for(MusicThreadListener mtl : musicThreadListener)
					mtl.startSliderThread();
			}
		});
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(MusicSelectionListener msl : musicSelectionListener)
					msl.lastMusic();
				for(MusicThreadListener mtl : musicThreadListener)
					mtl.startSliderThread();
			}
		});
		
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(MusicSelectionListener msl : musicSelectionListener)
					msl.nextMusic();
				for(MusicThreadListener mtl : musicThreadListener)
					mtl.startSliderThread();
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

	public static List<MusicSelectionListener> getMusicSelectionListener() {
		return musicSelectionListener;
	}

	public static void setMusicSelectionListener(
			List<MusicSelectionListener> musicSelectionListener) {
		Control.musicSelectionListener = musicSelectionListener;
	}
}
