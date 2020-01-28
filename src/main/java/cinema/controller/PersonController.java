package cinema.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cinema.persistance.entity.Movie;
import cinema.persistance.entity.Person;
import cinema.service.IPersonService;

@RestController
@RequestMapping("/api/person")
public class PersonController {
	
	@Autowired
	IPersonService personService;

	@GetMapping
	@ResponseBody
	public List<Person> allPersons() {
		return personService.getAllPersons();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Optional<Person> singlePerson(@PathVariable("id") int id) {
		return personService.getPersonById(id);
	}
	
	@GetMapping("/findByYear")
	@ResponseBody
	public Set<Person> findPersonByYear(@RequestParam("y") int year) {
		return personService.getPersonByYear(year);
	}
	
	@PostMapping("/addNewPerson")
	@ResponseBody
	public Person addNewPerson(@RequestBody Person newPerson) {
		return personService.addNewPerson(newPerson);
	}
	
	@PostMapping("/findByNationality")
	@ResponseBody
	public Set<Person> getPersonByNationality(@RequestParam("n") String nationality) {
		return personService.getPersonByNationality(nationality);
	}
	
}
