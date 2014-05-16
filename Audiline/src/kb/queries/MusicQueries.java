package kb.queries;

import java.io.File;
import java.util.List;

import javax.persistence.*;

import kb.misc.FillDataOptimizer;

import org.jaudiotagger.audio.*;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import kb.entities.Music;

public class MusicQueries
{
	private EntityManager em;
	
	public MusicQueries(){
		setEM();
	}
	
	private void setEM(){
		em = Persistence.createEntityManagerFactory("Audiline").createEntityManager();
	}
	
	public List<Music> getAllMusic(){
		Query q = em.createNamedQuery("getAllMusic");
		List<Music> list = q.getResultList();
		return list;
	}
	
	/**
	 * Die Liste der Dateien wird zunächst gefiltert. Die DB wird abgefragt ob die
	 * vorhandene Datei schon in der DB gespeichert wurde.
	 * 
	 * Für jede neue Datei wird ein neuer Datensatz angelegt, bestehend aus:
	 * <li>Artist
	 * <li>Titel
	 * <li>Genre
	 * <li>Dauer
	 * <li>Absoluter Pfadname
	 * <li>Ordner
	 * <li>Cover (noch nicht vorhanden)
	 * 
	 * @param files - Liste von Dateien
	 */
	public void fillDatabase(List<File> files){
		//Es wird überprüft ob die Dateien im Ordner schon in der DB vorhanden sind
		FillDataOptimizer fdp = new FillDataOptimizer();
		fdp.optimizeFileList(files, getAllMusic());
		
		setEM();
		
		AudioFile af;
		Tag tag;
		AudioHeader ah;
		Music m;
		
		String artist 	= "";
		String genre 	= "";
		String title 	= "";
		String duration = "";
		
		em.getTransaction().begin();
		
		for(File f : files){
			try{
				//Mittels JAudioTagger werden die Tags aus der Musikdatei extrahiert
				af = AudioFileIO.read(f);
				tag = af.getTag();
				
				//Wird benötigt um die Länge der Datei herauszufinden
				ah = af.getAudioHeader();
				
				if(tag != null){
					artist = tag.getFirst(FieldKey.ALBUM_ARTIST).toString();
					title = tag.getFirst(FieldKey.TITLE).toString();
					genre = tag.getFirst(FieldKey.GENRE.toString());
					duration = getDuration(ah.getTrackLength());
				}else{
					//Falls keine Tags vorhanden sind, wird nur 
					//der Titelname als Interpret und Titelnname angegeben
					artist = f.getName();
					title = f.getName();
				}
				
				//Ein neues Musicobjekt wird erstellt und in die DB geschrieben
				m = new Music();
				m.setArtist(artist);
				m.setGenre(genre);
				m.setTitle(title);
				m.setDuration(duration);
				m.setAbsoultePath(f.getAbsolutePath());
				m.setDirectory(f.getAbsolutePath().substring(0, f.getAbsoluteFile().toString().lastIndexOf(File.separator)));
				m.setCover(null);
				
				em.persist(m);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
		em.getTransaction().commit();
	}

	private String getDuration(int i){
		String[] time = splitTime(i);
		return time[0] + ":" + time[1] + ":" + time[2];
	}

	/**
	 * Die Dauer des Titels ist ein großer int Wert. Dieser wird
	 * zunächst in Stunden geteilt und dann in Sekunden und Minuten.
	 * 
	 * @param biggy	- Die Dauer des Musiktitels
	 * @return		- StringArray welches [Stunde, Minute, Sekunde] enthält
	 */
	public String[] splitTime(int biggy){
		//Großer Millisekundenwert
		long longVal = biggy;
		
		//Wird in Stunden aufgeteilt
	    int hours = (int) longVal / 3600;
	    
	    //Miunten (gerundet)
	    int remainder = (int) longVal - hours * 3600;
	    int mins = remainder / 60;
	    
	    //Sekunden (gerundet)
	    remainder = remainder - mins * 60;
	    int secs = remainder;

	    //Die erhaltenen Int Werte werden in Strings umgewandelt
	    String hoursS = String.valueOf(hours);
	    String minsS = String.valueOf(mins);
	    String secsS = String.valueOf(secs);
	    
	    //Falls die Länge des Strings kleiner als 2 ist, so wird eine Null
	    //an die erste Stelle davorgeschrieben
	    if(hoursS.length()<2)
	    	hoursS = "0"+hoursS;
	    if(minsS.length()<2)
	    	minsS = "0"+minsS;
	    if(secsS.length()<2)
	    	secsS = "0"+secsS;
	    
	    //Ein Array wird aus den Strings gebaut
	    String[] ints = {hoursS , minsS , secsS};
	    return ints;
	}
}
