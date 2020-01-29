package cinema.persistance.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ActorIdKey implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idMovie;
	private Integer idPerson;
	
	public ActorIdKey() {}

	public ActorIdKey(Integer idMovie, Integer idPerson) {
		super();
		this.idMovie = idMovie;
		this.idPerson = idPerson;
	}

	public Integer getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(Integer idMovie) {
		this.idMovie = idMovie;
	}

	public Integer getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Integer idPerson) {
		this.idPerson = idPerson;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMovie == null) ? 0 : idMovie.hashCode());
		result = prime * result + ((idPerson == null) ? 0 : idPerson.hashCode());
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
		ActorIdKey other = (ActorIdKey) obj;
		return Objects.equals(getIdMovie(), other.getIdMovie()) && Objects.equals(getIdPerson(), other.getIdPerson());
	}
	
	
}
