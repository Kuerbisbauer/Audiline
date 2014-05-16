package kb.misc;

import kb.entities.Music;

public class MusicFiles /*extends Music*/{
	private Music m;
	private boolean played;
	
	
	public MusicFiles(Music m, boolean b){
		this.played = b;
		this.m = m;
	}
	
	public MusicFiles(){
		//Wird benötigt damit es serialisierbar ist
	}
	
	@Override
	public String toString(){
		return m.getTitle();
	}

	public boolean isPlayed(){
		return played;
	}
	
	public void setPlayed(boolean played){
		this.played = played;
	}
	
	public Music getM(){
		return m;
	}
	
	public void setM(Music m){
		this.m = m;
	}
}
