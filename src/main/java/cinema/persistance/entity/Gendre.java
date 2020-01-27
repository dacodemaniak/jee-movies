package cinema.persistance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gendre")
public class Gendre {
	private Integer id_gendre;
	private String gendre_name;
	
	public Gendre() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_gendre")
	public Integer getId_gendre() {
		return id_gendre;
	}

	public void setId_gendre(Integer id_gendre) {
		this.id_gendre = id_gendre;
	}

	public String getGendre_name() {
		return gendre_name;
	}

	public void setGendre_name(String gendre_name) {
		this.gendre_name = gendre_name;
	}
	
}
