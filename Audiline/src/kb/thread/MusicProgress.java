package kb.thread;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import kb.interfacs.MusicThreadListener;
import kb.musicPlayer.MusicPlayer;

public class MusicProgress extends JPanel implements MusicThreadListener{

	/*
	 * ######################################
	 * Swing Komponenten
	 * ######################################
	 */
	private JSlider slider = new JSlider();
	private JLabel currentTime = new JLabel("");
	private JLabel maxTime	= new JLabel("");
	
	/*
	 * ######################################
	 * Klassen
	 * ######################################
	 */
	
	private Thread 					thread;
	private TimeThread 				timeThread;
	private MusicProgressOptions 	musicProgressOptions 	= new MusicProgressOptions();
	
	public MusicProgress(){
		buildGUI();
	}

	private void buildGUI() {
		setLayout(new BorderLayout());
		slider.setValue(0);
		
		this.add(currentTime, BorderLayout.WEST);
		this.add(slider, BorderLayout.CENTER);
		this.add(maxTime, BorderLayout.EAST);
		
		addActionListener();
	}

	private void addActionListener() {
		slider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				sliderRelease();
			}
		});
	}

	/**
	 * Wenn die linke Maustaste wieder losgelassen wird, wird der Wert
	 * des Sliders ausgewertet und die Zeit an der Stelle ausgerechnet.
	 * Die Zeit des Musiktitels wird an diese vorgegebene Stelle versetzt.
	 */
	protected void sliderRelease() {
		MediaPlayer mediaPlayer = MusicPlayer.getCurrentPlayer();
		Duration durationMusic = new Duration(getSliderValue()*1000);
		setSliderValue(getSliderValue());
		mediaPlayer.seek(durationMusic);
	}
	
	public void setSliderValue(int i){
		slider.setValue(i);
	}
	
	public int getSliderValue(){
		return slider.getValue();
	}
	
	public void resetSlider(){
		setSliderValue(0);
	}
	
	public void setCurrentTime(int i){
		currentTime.setText(musicProgressOptions.setCurrentTime(slider, i));
		setSliderValue(i);
	}
	
	public void setSliderMax(int i){
		maxTime.setText(musicProgressOptions.setSliderMax(slider, i));
	}

	@Override
	public void startSliderThread() {
		MediaPlayer mediaPlayer = MusicPlayer.getCurrentPlayer();
		setSliderMax((int) mediaPlayer.getTotalDuration().toSeconds());
		resetSlider();
		startTimeThread();
	}

	private void startTimeThread() {
		thread = new Thread(timeThread = new TimeThread(this));
		thread.start();
	}
}
