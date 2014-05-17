package kb.interfacs;

public interface MusicSelectionListener {
	/**
	 * Der n�chste Musiktitel wird im JTree und in PlaylistWatch ausgew�hlt
	 */
	public void nextMusic();
	
	/**
	 * Der vorherige Musiktitel wird im JTree und in PlaylistWatch ausgew�hlt
	 */
	public void lastMusic();
}
