package kb.entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: MusicToGroup
 *
 */
@Entity
@Table(name="MusicToGroup")
public class MusicToGroup implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public MusicToGroup() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="mtg_id")
	private int id;
	
	@JoinColumn(name="music_fs")
	private Music music;
	
	@JoinColumn(name="groups_fs")
	private Groups groups;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Music getMusic()
	{
		return music;
	}

	public void setMusic(Music music)
	{
		this.music = music;
	}

	public Groups getGroups()
	{
		return groups;
	}

	public void setGroups(Groups groups)
	{
		this.groups = groups;
	}
}
