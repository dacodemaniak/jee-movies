package cinema.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cinema.persistance.entity.Person;
import cinema.persistance.repository.PersonRepository;
import cinema.service.IPersonService;

@Service
@Transactional
public class PersonService implements IPersonService{
	
	@Autowired
	PersonRepository personRepository;

	@Override
	public List<Person> getAllPersons() {
		return personRepository.findAll();
	}

	@Override
	public Optional<Person> getPersonById(int id) {
		return personRepository.findById(id);
	}

	@Override
	public Set<Person> getPersonByYear(int year) {
		return personRepository.findByBirthDateYear(year);
	}

	@Override
	public Set<Person> getPersonByName(String name) {
		return personRepository.findByName(name);
	}

	@Override
	public Set<Person> getActorsByIdPerson(int idPerson) {
		return personRepository.findActorsByidPerson(idPerson);
	}

	@Override
	public Person addNewPerson(Person newPerson) {
		return personRepository.save(newPerson);
	}

}
