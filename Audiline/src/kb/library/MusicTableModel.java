package kb.library;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import kb.queries.MusicQueries;
import kb.entities.Music;

public class MusicTableModel extends AbstractTableModel
{

	private List<Music> listm;
	private MusicQueries mq = new MusicQueries();
	
	public MusicTableModel()
	{
		this.listm = mq.getAllMusic();
	}

	@Override
	public int getColumnCount()
	{
		return 4;
	}

	@Override
	public int getRowCount()
	{
		return listm.size();
	}
	
	@Override
	public String getColumnName(int col)
	{
		String name = "";
		
		switch(col)
		{
		case 0:
			name = "Baustelle";
			break;
		case 1:
			name = "Titel";
			break;
		case 2:
			name = "Artist";
			break;
		case 3:
			name = "Länge";
			break;
		}
		
		return name;
	}

	@Override
	public Object getValueAt(int row, int col)
	{
		Music m = listm.get(row);
		Object o = null;
		
		switch(col)
		{
			case 0:
				o = m.getId();
				break;
			case 1:
				o = m.getTitle();
				break;
			case 2:
				o = m.getArtist();
				break;
			case 3:
				o = m.getDuration();
				break;
			case 4:
				o = m.getAbsoultePath();
				break;
			case 5:
				o = m.getGenre();
				break;
			case 6:
				o = m.getDirectory();
		}
		
		return o;
	}
	
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
	
	public List<Music> getList()
	{
		return listm;
	}
}