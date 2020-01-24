package cinema.data;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

public class Person {
	
	private String name;
	private LocalDate birthdate;
	
	public Person(String name, LocalDate birthdate) {
		super();
		this.name = name;
		this.birthdate = birthdate;
	}

	public Person(String name) {
		this(name, null);
	}

	public String getName() {
		return name;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	@Override
	public String toString() {
		return name + " (" + Objects.toString(birthdate, "unknow") + ") ";
	}

	public OptionalInt getAge() {
		if (Objects.isNull(birthdate)) {
			return OptionalInt.empty();
		}
		LocalDate todayFull = LocalDate.now();// delta année - 1 si on a pas atteint la "date"
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
	
	//TODO : equals +
}		

