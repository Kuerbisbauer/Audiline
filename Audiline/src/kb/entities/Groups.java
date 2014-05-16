package kb.entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Group
 *
 */
@Entity
@Table(name="groups")
public class Groups implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Groups() {
		super();
	}
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="g_id")
	private int id;
	
	@Column(name="g_name")
	private String name;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
}
