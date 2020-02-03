package cinema.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cinema.dto.FullPerson;
import cinema.dto.LightPerson;
import cinema.persistance.entity.Person;
import cinema.persistance.repository.MovieRepository;
import cinema.persistance.repository.PersonRepository;
import cinema.service.IPersonService;

@Service
@Transactional
public class PersonService implements IPersonService{
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	MovieRepository movieRepository;

	@Override
	public List<LightPerson> getAllPersons() {
		List<Person> personEntity = personRepository.findAll();
		return personEntity.stream()
				.map(pe -> mapper.map(pe, LightPerson.class))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<FullPerson> getPersonById(int id) {
		return personRepository.findById(id)
				.map(pe -> mapper.map(pe, FullPerson.class));
	}
	
	@Override
	public Set<LightPerson> getPersonByName(String name) {
		Set<Person> personEntity = personRepository.findByName(name);
		return personEntity.stream()
				.map(pe -> mapper.map(pe, LightPerson.class))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<LightPerson> getPersonByYear(int year) {
		Set<Person> personEntity = personRepository.findByBirthDateYear(year);
		return personEntity.stream()
				.map(pe -> mapper.map(pe, LightPerson.class))
				.collect(Collectors.toSet());
	}


	@Override
	public Optional<LightPerson> getMovieDirector(int id_movie) {
		return movieRepository.findById(id_movie)
		.map(me -> {
			Person d = me.getDirector();
			return Objects.isNull(d) ? null : mapper.map(d, LightPerson.class);
		});
	}

	@Override
	public List<LightPerson> getMovieActors(int id_movie) {
		return movieRepository.findById(id_movie)
				.map(me -> me.getActors().stream()
						.map(a -> mapper.map(a, LightPerson.class))
						.collect(Collectors.toList()))
				.orElse(List.of());
	}	
	
	@Override
	public FullPerson addNewPerson(FullPerson personDto) {
		Person personEntity = mapper.map(personDto, Person.class);
		personRepository.save(personEntity);
		mapper.map(personEntity, personDto);
		return personDto;
	}
}
