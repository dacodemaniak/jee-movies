package cinema.persistance.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.persistance.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	Set<Person> findByName(String title);
	
}