package kb.library;

import kb.misc.FileSearch;
import kb.queries.MusicQueries;

public class LibraryBasics {

	public LibraryBasics(){
		
	}

	public void fillLibrary(MusicTableModel mtm, String path) {
		FileSearch fileSearch = new FileSearch();
		MusicQueries musicQueries = new MusicQueries();
		
		musicQueries.fillDatabase(fileSearch.getAllFiles(path));
	}
}
