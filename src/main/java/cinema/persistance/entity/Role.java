package cinema.persistance.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

public class Role {

	@ManyToMany()
	@JoinTable(name = "Role", 
		joinColumns = 
			@JoinColumn(name = "id_film"),
		inverseJoinColumns = 
			@JoinColumn(name = "id_acteur"))
	List<Person> acteurs = new ArrayList<>();
	public List<Person> getActeurs() {
		return acteurs;
	}
}
