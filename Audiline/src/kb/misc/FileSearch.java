package kb.misc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

public class FileSearch
{
	private List<File> files = new ArrayList<File>();

	/**
	 * Durchsucht Ordner nach allen verfügbaren Dateien.
	 * Diese werden dann überprüft ob die gefundene Datei ein MP3 Format
	 * ist. Wenn ja, wird sie in die Liste der MP3 Dateien hinzugefügt.
	 * 
	 * @param p	- Absoluter Pfad Name zum Ordner
	 * @return	- Liste von MP3 Dateien
	 */
	public List<File> getAllFiles(String p){
		File directory = new File(p);
		 
        File[] fList = directory.listFiles();
        
        for (File file : fList){
        	if (file.isFile()){
        		if(isMP3(file))
        			addToList(file);
        	}else if (file.isDirectory())
                getAllFiles(file.getAbsolutePath());
        }
        
		return files;
	}

	/**
	 * Überprüft anhand der Dateinamenserweiterung ob die Datei ein mp3 File ist
	 * 
	 * @param file 	- Zu überprüfende Datei
	 * @return		- Datei MP3? True | False
	 */
	private boolean isMP3(File file){
		String extensionFile_str = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".")+1);
		
		if (extensionFile_str.equals("mp3"))
			return true;
		else
			return false;
	}
	
	/**
	 * MP3 Dateien werden der Liste hinzugefügt
	 * 
	 * @param file - MP3 Datei
	 */
	public void addToList(File file){
		files.add(file);
	}

	/**
	 * Mittels Filechooser kann ein Verzeichnis ausgewählt werden.
	 * 
	 * @return - Absoulter Pfad zum Verzeichnis
	 */
	public static String chooseDirectory() {
		String absolutePath;
		
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnValue = jfc.showOpenDialog(null);
		
		if(returnValue == JFileChooser.APPROVE_OPTION)
			return absolutePath = jfc.getSelectedFile().getAbsolutePath();
			
		return null;
	}
	
	/**
	 * Mittels Filechooser kann eine Datei ausgewählt werden.
	 * @param string 
	 * 
	 * @return - Absoulter Pfad zur Datei
	 */
	public static String chooseFile(String string) {
		String absolutePath;
		
		JFileChooser jfc = new JFileChooser();
		
		int returnValue;
		
		if(string.equals("Load"))
			returnValue = jfc.showOpenDialog(null);
		else
			returnValue = jfc.showSaveDialog(null);
		
		if(returnValue == JFileChooser.APPROVE_OPTION)
			return absolutePath = jfc.getSelectedFile().getAbsolutePath();
			
		return null;
	}
}
