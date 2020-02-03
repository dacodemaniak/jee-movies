package cinema.persistance.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "persons")
public class Person {
	private Integer idPerson;
	private String name;
	private LocalDate birthdate;
	private	List<String> nationalities = new ArrayList<String>();
	private String biography;
	private List<Actor> roles = new ArrayList<Actor>();
	
	public Person() {
		super();
	}
	
	public Person(String name) {
		this(null,name,null, null,null);
	}

	public Person(Integer idPerson, String name) {
		this(idPerson, name, null, null, null);
	}
	
	public Person(String name, LocalDate birthdate) {
		this(null, name, birthdate,null,null);
	}
	
	public Person(String name, LocalDate birthdate,List<String> nationalities) {
		this(null, name, birthdate, nationalities, null);
	}

	public Person(Integer idPerson, String name, LocalDate birthdate, List<String> nationalities, String biography) {
		super();
		this.idPerson = idPerson;
		this.name = name;
		this.birthdate = birthdate;
		this.nationalities = nationalities ;
		this.biography = biography;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_person")
	public Integer getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Integer idPerson) {
		this.idPerson = idPerson;
	}

	@Column(nullable = false, length = 255)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	
	@ElementCollection
	@CollectionTable(name = "nationality",
		joinColumns=
	        @JoinColumn(name="id_person")
	)
	public List<String> getNationalities() {
		return nationalities;
	}

	public void setNationalities(List<String> nationalities) {
		this.nationalities = nationalities;
	}

	@Column(name = "biography")
	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	@OneToMany(targetEntity = Actor.class, mappedBy = "person")
	public List<Actor> getRoles() {
		return roles;
	}

	public void setRoles(List<Actor> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(name); //pour eviter de faire de + "" + ""+..etc
		return builder.append(" (")
					.append(Objects.toString(birthdate, "unknow"))
					.append(')')
					.append('#')
					.append(idPerson)
					.append(" (")
					.append(nationalities)
					.append(") ")
					.toString(); 
	}
	
}
