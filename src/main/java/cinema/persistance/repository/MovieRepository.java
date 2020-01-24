package cinema.persistance.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.persistance.entity.Movie;
import cinema.persistance.entity.Person;

public interface MovieRepository extends JpaRepository<Movie, Integer>{
	Set<Movie> findByTitle(String title);
	Set<Movie> findByDirector(Person director);
	Set<Movie> findByTitleContainingIgnoreCase(String partialTitle);
	Set<Movie> findByYear(int year);
	Set<Movie> findByYearGreaterThan(int year2);
	Set<Movie> findByYearBetween(int StarttYear, int LastYear);
	Set<Movie> findByTitleAndYear(String title, int year3);
	Set<Movie> findByActorsIdPerson(int idPerson);
	Set<Movie> findByActors(Person actor);
	Set<Movie> findByActorsNameEndingWith(String name);

}
