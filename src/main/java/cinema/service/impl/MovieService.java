package cinema.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cinema.dto.FullMovie;
import cinema.dto.LightMovie;
import cinema.persistance.entity.Movie;
import cinema.persistance.entity.Person;
import cinema.persistance.repository.MovieRepository;
import cinema.persistance.repository.PersonRepository;
import cinema.service.IMovieService;

@Service
@Transactional
public class MovieService implements IMovieService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	PersonRepository personRepository;

	@Override
	public List<LightMovie> getAllMovies() {
		List<Movie> movieEntity =  movieRepository.findAll();
		return movieEntity.stream()
				.map(me -> mapper.map(me, LightMovie.class))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<FullMovie> getMovieById(int idMovie) {
		return	movieRepository.findById(idMovie)
				.map(me -> mapper.map(me, FullMovie.class));
	}

	@Override
	public Set<LightMovie> getMovieByPartialTitle(String partialTitle) {
		Set<Movie> movieEntity =  movieRepository.findByTitleContainingIgnoreCase(partialTitle);
		return movieEntity.stream()
				.map(me -> mapper.map(me, LightMovie.class))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<LightMovie> getMovieByTitle(String title) {
		Set<Movie> movieEntity = movieRepository.findByTitle(title);
		return movieEntity.stream()
				.map(me -> mapper.map(me, LightMovie.class))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<LightMovie> getMovieByYear(int year) {
		Set<Movie> movieEntity = movieRepository.findByYear(year);
		return movieEntity.stream()
				.map(me -> mapper.map(me, LightMovie.class))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<LightMovie> getMovieByYearBetween(int year, int year_end) {
		Set<Movie> movieEntity = movieRepository.findByYearBetween(year, year_end);
		return movieEntity.stream()
				.map(me -> mapper.map(me, LightMovie.class))
				.collect(Collectors.toSet());
	}

	@Override
	public FullMovie addMovie(FullMovie movieDto) {
		Movie movieEntity = mapper.map(movieDto, Movie.class);
		movieRepository.save(movieEntity);
		mapper.map(movieEntity, movieDto);
		return movieDto;
	}

	@Override
	public Optional<FullMovie> addActorToMovie(int id_movie, int id_person) {
		return movieRepository.findById(id_movie)
		.flatMap(me -> personRepository.findById(id_person)
				.map(( Person a) -> {
					me.getActors().add(a);
					movieRepository.flush();
					return mapper.map(me, FullMovie.class);
				}));
	}
	
	@Override
	public Optional<FullMovie> addDirectorToMovie(int id_movie, int id_person) {
		return movieRepository.findById(id_movie)
				.flatMap(me -> personRepository.findById(id_person)
						.map(d -> {
							me.setDirector(d);
							movieRepository.flush();
							return mapper.map(me, FullMovie.class);
						}));
	}

	@Override
	public Optional<FullMovie> modifyMovie(FullMovie movieDto) {
		Optional<Movie> movieEntity = movieRepository.findById(movieDto.getIdMovie())
				.map(me -> mapper.map(me, Movie.class));

		movieEntity.ifPresent(m-> {
			m.setTitle(movieDto.getTitle());
			m.setYear(movieDto.getYear());
			m.setDuration(movieDto.getDuration());
			movieRepository.flush();
		});

		return movieEntity.map(me-> mapper.map(me, FullMovie.class));
	}

	@Override
	public FullMovie deleteMovie(int id_movie) {
		var movieEntity = movieRepository.findById(id_movie);

		movieEntity.ifPresent(m -> {
			movieRepository.delete(m);
			movieRepository.flush();
		});
		return mapper.map(movieEntity, FullMovie.class);
	}
}
