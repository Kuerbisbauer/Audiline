package kb.thread;

import javax.swing.JLabel;

import javafx.scene.media.MediaPlayer.Status;
import kb.musicPlayer.MusicPlayer;

public class TimeThread implements Runnable{
	
	private MusicProgress musicProgress;
	
	public TimeThread(MusicProgress musicProgress){
		this.musicProgress = musicProgress;
	}
	
	@Override
	public void run() {
		while(MusicPlayer.getCurrentPlayer().getStatus() == Status.PLAYING){	
			try{
				musicProgress.setCurrentTime((int) MusicPlayer.getCurrentPlayer().getCurrentTime().toSeconds());
				Thread.sleep(1000);
			}catch(Exception e){
				return;
			}		
		}
		
		interruptThread();
	}

	public static void interruptThread(){
		Thread.currentThread().interrupt();
	}
}
