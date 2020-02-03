package cinema.service;  
import java.util.List; 
import java.util.Optional; 
import java.util.Set;

import cinema.dto.FullPerson;
import cinema.dto.LightPerson;
import cinema.persistance.entity.Person;  

public interface IPersonService {     
	List<LightPerson> getAllPersons();     
	Optional<FullPerson> getPersonById(int id);     
	Set<LightPerson> getPersonByYear(int year);     
	Set<LightPerson> getPersonByName(String name);
	Optional<LightPerson> getMovieDirector(int id_movie);
	List<LightPerson> getMovieActors(int id_movie);

	FullPerson addNewPerson(FullPerson newPerson);
}
