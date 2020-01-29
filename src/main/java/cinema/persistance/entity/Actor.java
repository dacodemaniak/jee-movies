package cinema.persistance.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity(name = "actor")
public class Actor {

	@EmbeddedId
	private ActorId id;
	
	@ManyToOne
    	@MapsId("id_movie")
    	@JoinColumn(name = "movie_id")
    Movie movie;
	
	@ManyToOne
    	@MapsId("id_person")
    	@JoinColumn(name = "person_id")
    Person person;
 
    private String role;

	public Actor() {
		super();
	}

	public Actor(Movie movie, Person person, String role) {
		super();
		this.movie = movie;
		this.person = person;
		this.role = role;
	}

	public ActorId getId() {
		return id;
	}

	public void setId(ActorId id) {
		this.id = id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    
}
