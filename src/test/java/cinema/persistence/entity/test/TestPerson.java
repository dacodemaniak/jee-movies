package cinema.persistence.entity.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import cinema.persistance.entity.Movie;
import cinema.persistance.entity.Person;
import cinema.persistance.repository.MovieRepository;
import cinema.persistance.repository.PersonRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestPerson {

	@Autowired
	PersonRepository repoPerson; //interface qui permet de créer une class 
	
	@Autowired
	MovieRepository repoMovie; //interface qui permet de créer une class 
	
	@Autowired
	EntityManager entityManager;
	

	@Test
	void testSave () {
		Person person = new Person ("Clint Eastwood", LocalDate.of(1930, 5, 31));
		repoPerson.save(person);
		System.out.println(person);
	}
	
	@Rollback(false)
	@Test
	void testSave2() {
		Person person = new Person ("Joss Whedon", LocalDate.of(1964, 6, 23));
		repoPerson.save(person);
		System.out.println(person);
	}
	
	
	@Rollback(false)
	@Test
	void testSave3() {
		Person person = new Person ("Jack Black", LocalDate.of(1969, 8, 28));
		repoPerson.save(person);
		System.out.println(person);
	}
	
	@Test
	void testGetByName() {
		var person = repoPerson.findByName("Christopher Nolan");
		System.out.println(person);
	}
	
	@Rollback(false)
	@Test
	void addNationalities() {
		var person = repoPerson.findById(9);		
		System.out.println(person.get().getNationalities());				
		var nat = List.of("American","Britannique");
		
		if(person.isPresent()) {
			person.get().getNationalities().addAll(nat);
			repoPerson.flush();
		}
	}
	
	@Rollback(false)
	@Test
	void testSaveData4() {
			var robert = new Person("Robert Downey Jr", LocalDate.of(1965, 4, 4));		
			var naomi = new Person("Naomi Watts", LocalDate.of(1968, 9, 28));
			var emma = new Person("Emma Stone", LocalDate.of(1988, 11, 6)); 	
			var ryan = new Person("Ryan Gosling", LocalDate.of(1980, 11, 12));	
			
			var persons= List.of(robert,naomi,emma,ryan);
			persons.forEach(repoPerson::save);
	}
	
}
