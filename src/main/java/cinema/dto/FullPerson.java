package cinema.dto;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Stream;

public class FullPerson extends LightPerson{
	
	private List<String> nationalities;
	private String biography;
	
	
	public List<String> getNationalities() {
		return nationalities;
	}

	public void setNationalities(List<String> nationalities) {
		this.nationalities = nationalities;
	}
	
	public void addAllNationalities(Collection<? extends String> nationalities) {
		this.nationalities.addAll(nationalities);
	}
	
	public void addAllNationalities(String... nationalities) {
			this.addAllNationalities(Arrays.asList(nationalities));
	}
	
	public Iterator<String> interatorNationalities() {
		return this.nationalities.iterator();
	}
	
	public Stream<String> streamNationalities() {
		return this.nationalities.stream();
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}
	
	

}		

