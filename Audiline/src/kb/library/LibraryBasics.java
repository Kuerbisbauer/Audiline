package kb.library;

import kb.misc.FileSearch;
import kb.queries.MusicQueries;

public class LibraryBasics {

	public LibraryBasics(){
		
	}

	/**
	 * Die DB wird mit Musikdateien befüllt.
	 * Es wird zunächst nach allen MP3 Dateien im Ordner "path" gesucht.
	 * Diese werden dann gefiltert (mit der DB verglichen ob die Datei schon
	 * vorhanden ist).
	 * MP3 Dateien die noch nicht in der Datenbank sind, werden in die
	 * Datenbank geschrieben.
	 * 
	 * @param path	- Absoulter Pfadname zum ausgewählten Ordner
	 */
	public void fillDatabase(String path) {
		FileSearch fileSearch = new FileSearch();
		MusicQueries musicQueries = new MusicQueries();
		
		musicQueries.fillDatabase(fileSearch.getAllFiles(path));
	}
}
