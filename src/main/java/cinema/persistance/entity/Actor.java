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
	private ActorIdKey pk = new ActorIdKey();
	
	@ManyToOne
    @MapsId("idMovie")
    private Movie movie;
	
	@ManyToOne
    @MapsId("idPerson")
    private Person person;
 
    private String role;

    public Actor() {}
    
	public Actor(Movie movie, Person person, String role) {
		super();
		this.movie = movie;
		this.person = person;
		this.role = role;
	}

	public ActorIdKey getPk() {
		return pk;
	}

	public void setPk(ActorIdKey pk) {
		this.pk = pk;
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
