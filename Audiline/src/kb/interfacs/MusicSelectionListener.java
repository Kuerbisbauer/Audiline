package kb.interfacs;

public interface MusicSelectionListener {
	/**
	 * Der nächste Musiktitel wird im JTree und in PlaylistWatch ausgewählt
	 */
	public void nextMusic();
	
	/**
	 * Der vorherige Musiktitel wird im JTree und in PlaylistWatch ausgewählt
	 */
	public void lastMusic();
}
