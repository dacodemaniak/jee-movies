package cinema.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cinema.persistance.entity.Movie;
import cinema.persistance.repository.MovieRepository;
import cinema.persistance.repository.PersonRepository;
import cinema.service.IMovieService;

@Service
@Transactional
public class MovieService implements IMovieService {
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	PersonRepository personRepository;
	

	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public Optional<Movie> getMovieById(int idMovie) {
		// TODO Auto-generated method stub
		return	movieRepository.findById(idMovie);	
	}

	@Override
	public Set<Movie> getMovieByPartialTitle(String partialTitle) {
		// TODO Auto-generated method stub
		return	movieRepository.findByTitleContainingIgnoreCase(partialTitle);	
	}

	@Override
	public Set<Movie> getMoviesByDirector(int idDirector) {
		// TODO Auto-generated method stub
		var directorOpt = personRepository.findById(idDirector);
		return directorOpt.map(d -> movieRepository.findByDirector(d))
				.orElseGet(() -> Collections.emptySet());
	}

	@Override
	public Set<Movie> getMovieByActor(int idActor) {
		// TODO Auto-generated method stub
		return movieRepository.findByActorsIdPerson(idActor);
	}
	

	

}
