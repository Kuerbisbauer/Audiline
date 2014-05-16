package kb.misc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import kb.entities.Music;

public class FillDataOptimizer
{
	
	//Überprüft die Datenbank und die Fileliste vom Betriebssystem und entfernt gleiche Pfade
	//Eine Liste von fehlenden Dateien wird zurückgegeben
	public List<File> optimizeFileList(List<File> fileDIRList, List<Music> listFromDB)
	{
 		List<Music> fl = listFromDB;
		List<File> fileDBList = extractAbsoultePath(fl);
		
		for(File f : fileDBList)
			if(fileDIRList.contains(f))
				fileDIRList.remove(f);
		
		return fileDIRList;
	}

	private List<File> extractAbsoultePath(List<Music> fl)
	{
		List<File> list = new ArrayList<File>();
		for(Music m : fl)
		{
			list.add(new File(m.getAbsoultePath()));
		}
		return list;
	}
}
