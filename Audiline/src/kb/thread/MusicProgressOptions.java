package kb.thread;

import javax.swing.JSlider;

public class MusicProgressOptions {

	public MusicProgressOptions(){
		
	}

	/**
	 * Teilt den mitgelieferten Integerwert in Minuten und Sekunden ein und gibt
	 * einen String im Format [mm:ss] zurück
	 * 
	 * Der maximale Wert des JSliders wird auf den mitgelieferten Integerwert
	 * gesetzt.
	 * 
	 * @param slider - JSlider in MusicProgress
	 * @param i		 - Dauer des Musiktitels in Sekunden
	 * @return		 - String der die Minuten und Sekunden des Titels angibt, Format: [mm:ss]
	 */
	public String setSliderMax(JSlider slider, int i) {
		String length = String.valueOf(i/60);
		String rest = String.valueOf(i%60);
		
		//Falls der Restwert nur aus einem Zeichen besteht, wird manuell eine Null davor gesetzt
		if(rest.length()==1)
			rest = "0"+rest;
		
		String maxTime = length+":"+rest;
		slider.setMaximum(i);
		
		return maxTime;
	}

	/**
	 * Siehe setSliderMax()
	 * 
	 * @param slider
	 * @param i
	 * @return
	 */
	public String setCurrentTime(JSlider slider, int i) {
		String length = String.valueOf(i/60);
		String rest = String.valueOf(i%60);
		
		//Falls der Restwert nur aus einem Zeichen besteht, wird manuell eine Null davor gesetzt
		if(rest.length()==1)
			rest = "0"+rest;
		
		String currentTime = length+":"+rest;
		
		return currentTime;
	}
}
