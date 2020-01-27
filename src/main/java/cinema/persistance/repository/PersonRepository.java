package cinema.persistance.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cinema.persistance.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	Set<Person> findByName(String name);
<<<<<<< HEAD
	Set<Person> findByNameContainingIgnoreCase(String Name);
=======
	Set<Person> findByNameContainingIgnoreCase(String firstName);
>>>>>>> b7e5b4e044e1f374c2661382eb9c87b0116e9b42
	
	@Query("select p from Person p where extract(year from p.birthdate) = ?1")
	Set<Person> findByBirthDateYear(int year);
}