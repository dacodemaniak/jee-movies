package cinema.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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

	@Override
	public Set<Movie> getMovieByTitle(String title) {
		// TODO Auto-generated method stub
		return movieRepository.findByTitle(title);
	}

	@Override
	public Set<Movie> getMovieByYear(int year) {
		// TODO Auto-generated method stub
		return movieRepository.findByYear(year);
	}

	@Override
	public Set<Movie> getMovieByYearBetween(int year, int year_end) {
		// TODO Auto-generated method stub
		return movieRepository.findByYearBetween(year, year_end);
	}

	@Override
	public Set<Movie> getMovieByGenres(String genres) {
		return movieRepository.findByGenresIgnoreCase(genres);
	}

	@Override
	public Movie addMovie(Movie movie) {
		// TODO Auto-generated method stub
		return movieRepository.save(movie);
	}

	
	@Override
	public Optional<Movie> modifyMovie(Movie movie) {
		var optMovie = movieRepository.findById(movie.getId_movie());
		optMovie.ifPresent(m -> {
			m.setTitle(movie.getTitle());
			m.setYear(movie.getYear());
			m.setDuration(movie.getDuration());
			m.setDirector(movie.getDirector());
		});	
		movieRepository.flush();
		return optMovie;
	}

	@Override
	public Optional<Movie> addActorToMovie(int id_movie, int id_actor) {
		var movieOpt = movieRepository.findById(id_movie);
		var actorOpt = personRepository.findById(id_actor);
		
		if (movieOpt.isPresent() && actorOpt.isPresent()) {
			movieOpt.get().getActors().add(actorOpt.get());
			movieRepository.flush();
		}
		return movieOpt;
	}

	@Override
	public Optional<Movie> addDirectorToMovie(int id_movie, int id_director) {
		var movieOpt = movieRepository.findById(id_movie);
		var directorOpt = personRepository.findById(id_director);
		
		if (movieOpt.isPresent() && directorOpt.isPresent() ) {
			movieOpt.get().setDirector(directorOpt.get());
			movieRepository.flush();
		}
		return movieOpt;
	}

	@Override
	public Optional<Movie> deleteMovie(int id_movie) {
		var delMovie = movieRepository.findById(id_movie);
		delMovie.ifPresent(m -> {
			movieRepository.delete(m);
			movieRepository.flush();
		});
		return delMovie;
	}
}
