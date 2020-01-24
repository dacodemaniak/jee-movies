package test.cinema.data;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import cinema.data.Movie;
import cinema.data.Person;

class TestMovie {

	@Test
	void test() {
				
		Movie movie = new Movie("Joker", 2019, 165);// création de l'objet movie avec new
		Movie movie2 = new Movie("Parasite", 2019, 132);
		Movie movie3 = new Movie("interstellar", 2014);
		List<Movie> movies = List.of(movie,movie2,movie3);
		
		System.out.println("movie: " + movies);
		System.out.println("Title :" + movie.getTitle());
		Movie oneMovie = movies.get(0);
		System.out.println("Movie tonight: " + oneMovie);
		
		movie.setYear(1900);
		System.out.println(movie);
		System.out.println(movies.get(0));
		System.out.println(oneMovie);
		
		//add directors
		Person tp = new Person("Todd Phillips");
		movie.setDirector(tp);
		System.out.println(movie + " réalisé par " + movie.getDirector());
		System.out.println(movie.getTitle() + " réalisé par " + movie.getDirector().getName());
		
		//Clint Eastwood
		Person clint = new Person("Clint Eastwood", LocalDate.of(1930, 5, 31));
		System.out.println(clint + " a " + clint.getAge().getAsInt() + " ans"); 
		//getAsInt affiche l'info dans la boite optionnel car on est sur qu'elle st la 
		Movie MovieGT = new Movie ("Gran Torino", 2008, 116, clint);
		Movie MovieI = new Movie ("Impitoyable", 1992, clint);
		System.out.println("Director of " + MovieGT.getTitle() + " : " + MovieGT.getDirector().getName());
	}
	
//	@Test
//	void testEquals() {
//		Movie movieJ = new Movie("Joker",2019,165);
//		Movie movieP = new Movie("Parasite",2019, 132);
//		Movie movie = movieJ;
//		System.out.println(movieJ == movieP);
//		System.out.println(movieJ == movieJ);
//		System.out.println(movie == movieJ);
//	}
	
	@Test
	void testEquals() {
		Movie movieChaosI = new Movie ("Chaos", 2005);
		Movie movieChaosII = new Movie("Chaos",2005);
		System.out.println(movieChaosI == movieChaosII);
		System.out.println(movieChaosI.equals(movieChaosII));
	}
	
	@Test
	void testMovieAsObject() {
		Movie movieChaosI = new Movie("Chaos", 2005);
		Object obj = "Chaos";//movieChaosI;
		Movie movie = (Movie) obj;
		System.out.println(movie.getTitle());
	}
	
	@Test
	void testEqualsNull() {
		Movie movieChaosI = new Movie ("Chaos", 2005);
		Movie movieNull = null;
		System.out.println(movieChaosI == movieNull);
		System.out.println(movieChaosI.equals(movieNull));
	}
	
	@Test
	void testEqualsTitleNull() {
		Movie movieChaosI = new Movie ("Chaos", 2005);
		Movie movieTitleNull = new Movie(null,2005);
		System.out.println(movieChaosI == movieTitleNull);
		System.out.println(movieTitleNull == movieChaosI);
		System.out.println(movieChaosI.equals(movieTitleNull));
		System.out.println(movieTitleNull.equals(movieChaosI));
		
	}		
}
