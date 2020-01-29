package cinema.persistance.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ActorId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "movie_id")
    private Integer movie;
 
    @Column(name = "person_id")
    private Integer person;
    
	public ActorId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActorId(Integer movie, Integer person) {
		super();
		this.movie = movie;
		this.person = person;
	}

	public Integer getMovie() {
		return movie;
	}

	public void setMovie(Integer movie) {
		this.movie = movie;
	}

	public Integer getActor() {
		return person;
	}

	public void setActor(Integer person) {
		this.person = person;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movie == null) ? 0 : movie.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActorId other = (ActorId) obj;
		if (movie == null) {
			if (other.movie != null)
				return false;
		} else if (!movie.equals(other.movie))
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		return true;
	}
    
	
}
