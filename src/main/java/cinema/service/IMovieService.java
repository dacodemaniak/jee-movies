package cinema.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;



import cinema.persistance.entity.Movie;

public interface IMovieService {
	List<Movie> getAllMovies();
	Optional<Movie> getMovieById(int idMovie);
	Set<Movie> getMovieByPartialTitle(String partialTitle);
	Set<Movie> getMoviesByDirector(int idDirector);
	Set<Movie> getMovieByActor(int idActor);
	Set<Movie> getMovieByTitle(String title);
	Set<Movie> getMovieByYear(int year);
	Set<Movie> getMovieByYearBetween(int year, int year_end);
	Movie addMovie(Movie movie);
	
}
