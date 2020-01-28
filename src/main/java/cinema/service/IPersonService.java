package cinema.service;  
import java.util.List; 
import java.util.Optional; 
import java.util.Set;


import cinema.persistance.entity.Person;  

public interface IPersonService {     
	List<Person> getAllPersons();     
	Optional<Person> getPersonById(int id);     
	Set<Person> getPersonByYear(int year);     
	Set<Person> getPersonByName(String name);
	Set<Person> getPersonByNationality(String nationality);
//	Set<Person> getActorsByIdPerson(int idPerson);
	Person addNewPerson(Person newPerson);
	     
}
