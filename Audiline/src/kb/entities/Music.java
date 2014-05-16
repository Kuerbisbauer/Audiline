package kb.entities;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: MP3
 *
 */
@Entity
@Table(name="music")
@NamedQueries(
{
		@NamedQuery(name = "getAllMusic", query = "Select m From Music m order by m.artist"),
})

public class Music implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Music() {
		super();
	}
   
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="m_id")
	private int id;
	
	@Column(name="m_artist")
	private String artist;
	
	@Column(name="m_title")
	private String title;
	
	@Column(name="m_genre")
	private String genre;
	
	@Column(name="m_cover")
	private Blob cover;
	
	@Column(name="m_duration")
	private String duration;
	
	@Column(name="m_absolutepath")
	private String absoultePath;
 
	@Column(name="m_directory")
	private String directory;
	
	
	@Override
	public String toString()
	{
		return title;
	}
	
	
	public String getAbsoultePath()
	{
		return absoultePath;
	}
	
	public void setAbsoultePath(String absoultePath)
	{
		this.absoultePath = absoultePath;
	}

	public String getDirectory()
	{
		return directory;
	}

	public void setDirectory(String directory)
	{
		this.directory = directory;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getArtist()
	{
		return artist;
	}

	public void setArtist(String artist)
	{
		this.artist = artist;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getGenre()
	{
		return genre;
	}

	public void setGenre(String genre)
	{
		this.genre = genre;
	}

	public Blob getCover()
	{
		return cover;
	}

	public void setCover(Blob cover)
	{
		this.cover = cover;
	}

	public String getDuration()
	{
		return duration;
	}

	public void setDuration(String duration)
	{
		this.duration = duration;
	}
}
