package cinema.persistance.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cinema.persistance.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	Set<Person> findByName(String name);
	Set<Person> findByNameContainingIgnoreCase(String Name);
	
	@Query("select p from Person p where extract(year from p.birthdate) = ?1")
	Set<Person> findByBirthDateYear(int year);
}