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
		// TODO Auto-generated method stub
		return personRepository.findAll();
	}

	@Override
	public Optional<Person> getPersonById(int id) {
		// TODO Auto-generated method stub
		return personRepository.findById(id);
	}

	@Override
	public Set<Person> getPersonByYear(int year) {
		// TODO Auto-generated method stub
		return personRepository.findByBirthDateYear(year);
	}

	@Override
	public Set<Person> getPersonByName(String name) {
		// TODO Auto-generated method stub
		return personRepository.findByName(name);
	}

}
