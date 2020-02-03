package cinema.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import cinema.dto.FullMovie;
import cinema.dto.LightMovie;

public interface IMovieService {
	List<LightMovie> getAllMovies();
	Optional<FullMovie> getMovieById(int idMovie);
	Set<LightMovie> getMovieByPartialTitle(String partialTitle);
	Set<LightMovie> getMovieByTitle(String title);
	Set<LightMovie> getMovieByYear(int year);
	Set<LightMovie> getMovieByYearBetween(int year, int year_end);
	
	FullMovie addMovie(FullMovie movie);
	Optional<FullMovie> addActorToMovie(int id_movie, int id_person);
	Optional<FullMovie> addDirectorToMovie(int id_movie, int id_person);
	
	Optional<FullMovie> modifyMovie(FullMovie movie);
	
	FullMovie deleteMovie(int id_movie);
	
}
