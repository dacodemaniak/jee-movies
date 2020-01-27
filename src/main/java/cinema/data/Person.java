package cinema.data;

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

public class Person {
	
	private String name;
	private LocalDate birthdate;
	private List<String> nationalities;
	private String biography;
	
	public Person(String name, LocalDate birthdate,List<String> nataionalities,String biography) {
		super();
		this.name = name;
		this.birthdate = birthdate;
		this.nationalities = new ArrayList<>();
		this.biography = biography;
	}

	public Person(String name) {
		this(name, null, null, null);
	}
	
	public Person(String name, LocalDate birthdate) {
		this(name, birthdate, null, null);
	}
	
	public Person(String name, LocalDate birthdate, List<String> nationalities) {
		this(name, birthdate,nationalities, null);
	}

	public String getName() {
		return name;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	@Override
	public String toString() {
		return name + " (" + Objects.toString(birthdate, "unknow") + ") " + nationalities;
	}

	public OptionalInt getAge() {
		if (Objects.isNull(birthdate)) {
			return OptionalInt.empty();
		}
		LocalDate todayFull = LocalDate.now();// delta ann�e - 1 si on a pas atteint la "date"
		MonthDay birthday = MonthDay.of( 
				birthdate.getMonthValue(), 
				birthdate.getDayOfMonth());
		MonthDay today = MonthDay.now();
		int deltaYear = todayFull.getYear() - birthdate.getYear();
		if (today.compareTo(birthday) < 0) { //si today est plus petit que birthday
			 deltaYear --; 
		}
		return OptionalInt.of(deltaYear);
	
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
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

}		

