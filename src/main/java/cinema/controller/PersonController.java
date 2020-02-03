package cinema.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cinema.dto.FullPerson;
import cinema.dto.LightPerson;
import cinema.persistance.entity.Person;
import cinema.service.IPersonService;

@RestController
@RequestMapping("/api/person")
public class PersonController {
	
	@Autowired
	IPersonService personService;

	@GetMapping
	@ResponseBody
	public List<LightPerson> allPersons() {
		return personService.getAllPersons();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Optional<FullPerson> singlePerson(@PathVariable("id") int id) {
		return personService.getPersonById(id);
	}
	
	@GetMapping("/getMovieDirector/{id}")
	@ResponseBody
	Optional<LightPerson> getMovieDirector( @PathVariable("id") int id_movie){
		return personService.getMovieDirector(id_movie);
	}
	
	@GetMapping("/getActorsByMovie/{id}")
	@ResponseBody
	List<LightPerson> getMovieActors( @PathVariable("id") int id_movie){
		return personService.getMovieActors(id_movie);
	}
	
	@GetMapping("/findByYear")
	@ResponseBody
	public Set<LightPerson> findPersonByYear(@RequestParam("y") int year) {
		return personService.getPersonByYear(year);
	}
	
	@PostMapping("/addNewPerson")
	@ResponseBody
	public FullPerson addNewPerson(@RequestBody FullPerson newPerson) {
		return personService.addNewPerson(newPerson);
	}
<<<<<<< HEAD
	
	@PostMapping("/findByNationality")
	@ResponseBody
	public Set<Person> getPersonByNationality(@RequestParam("n") String nationality) {
		return personService.getPersonByNationality(nationality);
	}
	
=======
>>>>>>> 3f14f6a69607a923a7e49f2355c81c79e574a02b
}
