package kb.musicPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import kb.misc.MusicFiles;

public class MusicPlayer {

	
	/*
	 * ######################################
	 * Attribute
	 * ######################################
	 */
	private MediaPlayer player;
	private static MediaView	mediaView;
	
	private List<MediaPlayer> list 	= new ArrayList<MediaPlayer>();
	
	public MusicPlayer(){
		
	}
	
	/**
	 * Eine Liste von abspielbaren Titel wird vorbereitet.
	 * Außerdem wird automatisch, nachdem ein Titel zu Ende gespielt wurde,
	 * der nächste abgespielt.
	 */
	public void initialize() {
		if(mediaView != null)
			if(mediaView.getMediaPlayer().getStatus().equals(Status.PLAYING))
				mediaView.getMediaPlayer().stop();
		
		resetList();
		
		//Für jeden Musiktitel im im ausgewählten Zweig wird ein MediaPlayer angelegt
		//und in eine Liste geschrieben
		for(DefaultMutableTreeNode dmt : MusicToPlay.getMusicToPlay()){
			String filePath = ((MusicFiles) dmt.getUserObject()).getM().getAbsoultePath();
			MediaPlayer playIt = new MediaPlayer(new Media(new File(filePath).toURI().toString()));
			list.add(playIt);
		}
		
		//Der erste Titel in der Liste wird in die mediaView gelegt
		mediaView = new MediaView();
		mediaView.setMediaPlayer(list.get(0));
		
		//Wenn ein MediaPlayer mit der Wiedergabe fertig ist
		//so wird der nächste abgespielt
		for(int i = 0; i < list.size(); i++){
			final MediaPlayer playIt = list.get(i);
			
			playIt.setOnEndOfMedia(new Runnable() {
				@Override
				public void run() {
					next();
				}
			});
		}
	}

	/**
	 * Setzt die Liste von MediaPlayer zurück
	 */
	private void resetList(){
		list.clear();
	}
	/**
	 * Stoppt den aktuellen MediaPlayer
	 */
	public void stopPlayer(){
		getCurrentPlayer().stop();
	}
	
	public void play(){
		player = getCurrentPlayer();
		Status status = player.getStatus();
		
		//if(status.equals(Status.UNKNOWN))
		//	player.play();
		
		if(status.equals(Status.READY))
			player.play();
		
		if(status.equals(Status.PLAYING))
			player.pause();
		
		if(status.equals(Status.PAUSED) || status.equals(Status.STOPPED))
			player.play();
	}

	public static MediaPlayer getCurrentPlayer(){
		if(mediaView!=null)
			return mediaView.getMediaPlayer();
		else
			return null;
	}

	/**
	 * Der nächste Titel in der Liste wird abgespielt.
	 */
	public void next() {
		if(mediaView != null){
			//Falls Musik abgespielt wird, wird diese beendet
			if(getCurrentPlayer().getStatus().equals(Status.PLAYING))
				player.stop();
			
			//Division verhindert, dass der Player nicht aus dem Indexbereich springt
			MediaPlayer nextPlayer = list.get((list.indexOf(player)+1) % list.size());
			mediaView.setMediaPlayer(nextPlayer);
			
			play();
		}
	}

	/**
	 * Der vorige Titel in der Liste wird abgespielt.
	 */
	public void back() {
		if(mediaView != null){
			//Falls Musik abgespielt wird, wird diese beendet
			if(getCurrentPlayer().getStatus().equals(Status.PLAYING))
				player.stop();
			
			MediaPlayer nextPlayer;
			
			//Aktueller Index, Division verhindert, dass der Player nicht aus dem Indexbereich springt
			int index = (list.indexOf(player) - 1) % list.size();
			//Die Grenze der Liste wird um 1 verringert, damit diese nicht übersprungen wird
			int limit = list.size()-1;
			
			if(index != -1)
				nextPlayer = list.get(index);
			else
				nextPlayer = list.get(limit);
			
			mediaView.setMediaPlayer(nextPlayer);
			
			play();
		}
	}
}
