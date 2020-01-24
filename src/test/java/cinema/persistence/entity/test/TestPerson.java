package cinema.persistence.entity.test;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cinema.persistance.entity.Person;
import cinema.persistance.repository.PersonRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestPerson {

	@Autowired
	PersonRepository repoPerson; //interface qui permet de créer une class 
	
	@Autowired
	EntityManager entityManager;
	

	@Test
	void testSave () {
		Person person = new Person ("Clint Eastwood", LocalDate.of(1930, 5, 31));
		repoPerson.save(person);
		System.out.println(person);
	}
	
	@Test
	void testSave2() {
		Person person = new Person ("Joss Whedon", LocalDate.of(1964, 6, 23));
		repoPerson.save(person);
		System.out.println(person);
	}
}
