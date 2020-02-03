package cinema.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import cinema.persistance.entity.Person;

public class FullMovie extends LightMovie{
	
	private Integer duration;
	private Set<String> genres = new TreeSet<String>();
	private String synopsis;
	private List<Person> actors = new ArrayList<>();

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Set<String> getGenres() {
		return genres;
	}

	public void setGenres(Set<String> genres) {
		this.genres = genres;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public List<Person> getActors() {
		return actors;
	}

	public void setActors(List<Person> actors) {
		this.actors = actors;
	}
}

