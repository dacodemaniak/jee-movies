package test.cinema.data;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cinema.data.Movie;
import cinema.data.Person;

class TestCinema {  
	
	//attributs
	private List<Movie> movies;
	private List<Person> persons;
	
	@BeforeEach
	void initData() {
		persons = new ArrayList<>();
		Collections.addAll(persons,
				new Person ("Joaquin Phoenix", LocalDate.of(1974, 10, 28)),	//0
				new Person ("Gerard Darmon", LocalDate.of(1948, 2, 29)),	//1
				new Person ("Todd Phillips"),								//2
				new Person ("Clint Eastwood", LocalDate.of(1930, 5, 31)), 	//3
				new Person ("Bradley Cooper", LocalDate.of(1975, 1, 5)),	//4
				new Person("Gene Hackman", LocalDate.of(1930, 1, 30)),
				new Person("Morgan Freeman", LocalDate.of(1937, 6, 1))
				);
		var clint = persons.get(3);
		var todd = persons.get(2);
		
		movies = new ArrayList<>();
		Collections.addAll(movies,
				 new Movie("Joker", 2019, 165, todd),			 //0
				 new Movie("Parasite", 2019, 132),		 		 //1
				 new Movie("interstellar", 2014),				 //2
				 new Movie("Gran Torino", 2008, 116, clint),     //3
				 new Movie("Impitoyable", 1992, clint),		 	 //4
				 new Movie("American Sniper", 2014, 133, clint), //5
				 new Movie("Very Bad Trip", 2009, 100, todd), 	 //6
				 new Movie("Avengers", 2012, 145),
				 new Movie("Avengers: Endgame", 2019, 181),
				 new Movie("Captain Marvel", 2014, 123),
				 new Movie("Avengers: L'ère d'Ultron", 2015, 142),
				 new Movie("Avengers: Infinity War", 2018, 149),
				 new Movie("Night of the Day of the Dawn of the Son of the Bride of the Return of the Revenge of the Terror of the Attack of the Evil, Mutant, Hellbound, Flesh-Eating Subhumanoid Zombified Living Dead, Part 3", 2005)
				  );	
		
		movies.get(0).addActor(persons.get(0));
		movies.get(04).addAllActors(persons.get(3),persons.get(5),persons.get(6));
		var actorsParasite = List.of(
				new Person("Kang-ho Song"),
				new Person("Yeo-jeong Jo"),
				new Person("Woo-sik Choi"),
				new Person("Jeong-eun Lee"));
		persons.addAll(actorsParasite);
		movies.get(1).addAllActors(actorsParasite);
				
	}
	
//	
//	@Test
//	void testModifiableList() {
//		System.out.println(persons);
//		System.out.println(persons.getClass());
//		persons.add(new Person("Bradley Cooper", LocalDate.of(1975, 1, 5))); 
//		// can't add a new person (don't work) can't change "list of"
//		System.out.println(persons);
//	}
	
	@Test
	void displayMoviesForI() {
		System.out.println("*** Movie List ***");
		for (int i=0; i < movies.size(); i++) {
			var movie = movies.get(i);
			System.out.println(movie + " directed by " + movie.getDirector());
			
		}		
	}

	@Test
	void displayMoviesForEach() { 
		System.out.println("*** Movies List***");
		for (var movie: movies) { //iteration sur 1 donn�e it�rable 
		// pour chaque objet movie dans la list movies on fait ..
			System.out.println(" - " + movie + " directed by " + movie.getDirector());
		}
	}
	
	@Test
	void totalDurationOfMoviesDirectedByClintEastwood() {
		int totalDuration = 0;
		var clint = persons.get(3);
		for (var movie:movies) {
			if (clint.equals(movie.getDirector())) {
				//on peut demander a la var clint si elle est �gale � un directeur ou non 
				//mais on peut pas demander � quelque de chose de vide(certains film n'ont pas de directeur)
				//si il est �gal � la var clint
				totalDuration += movie.getDuration(); //totD = totD + movie.getduration()
			}
		}
		System.out.println("Total duration of movies directed by Clint Easwood: " + totalDuration + " mn");
	}
	
	
	@Test
	void totalDurationOfMoviesDirectedByClintEastwoodStreaming() {
		var clint = persons.get(3);
		 int totalDuration = movies.stream()
			.filter(m -> clint.equals(m.getDirector()))
			//.map(m -> m.getDuration()) 
			.mapToInt(Movie::getDuration)
			//.forEach(System.out::println);
			.sum();
		 System.out.println("Total duration of movies directed by Clint Easwood: " + totalDuration + " mn");
	}
	
	
	@Test
	void testSortedMovies() {
		SortedSet<Movie> sortedMovies = new TreeSet<>(
				Comparator.comparing(Movie::getYear, Comparator.reverseOrder())
				.thenComparing(Movie::getTitle));
				//(m1,m2) -> -1);
		sortedMovies.addAll(movies);
		System.out.println(movies);
				
	}
	
	@Test
	void testSortMovies() {
		Collections.sort(movies, //besoin d'un comparateur de movie
				Comparator.comparing(Movie::getYear, Comparator.reverseOrder()) // ref de fonction (::)
					.thenComparing(Movie::getTitle));
					//.thenComparing(m -> m.getTitle());
		System.out.println(movies);
		
	}
	
	@Test
	void testAvengersFirstYear() {
		OptionalInt firstyear = movies.stream()
		.filter(m -> m.getTitle().contains("Avengers")) 
		.mapToInt(Movie :: getYear)
		.min();
		if (firstyear.isPresent()) {
			System.out.println("La premi�re ann�e des films avengers est: " + firstyear.getAsInt()); 
	     } else {
	    	System.out.println("Pas de r�sultat");
		 }
		 //getAsInt pour demander d'afficher un OptionnelInt
	}
	
	
	@Test
	void testAvengersFirstLastYear() {
		var stats = movies.stream()
			.filter(m -> m.getTitle().contains("Avengers")) 
			.mapToInt(Movie :: getYear)
			.summaryStatistics();
		System.out.println("First year: " + stats.getMin());
		System.out.println("Last year: " + stats.getMax());
		
	}
	
	@Test
	void testListeChronologiqueAvengersMovie() {
		var avengersMovies = movies.stream()
		.filter(m -> m.getTitle().contains("Avengers")) 
		.collect(Collectors.toCollection(() -> new TreeSet<>( 
				Comparator.comparing(Movie::getYear)
				))); //cr�er une list pour la remplir apr�s
		System.out.println(avengersMovies);
		
	}
	
	@Test
	void titlesAvengersMovies() {
		var joinedTitles = movies.stream()
				.filter(m -> m.getTitle().contains("Avengers"))
				.map(Movie::getTitle)
				.collect(Collectors.joining(", "));
		System.out.println(joinedTitles);
	}
	
	@Test
	void testLimit() {
		movies.stream()
		.filter((Movie m) -> m.getYear() > 1900)
		.limit(5)
		.forEach(System.out::println);
	}
	
	@Test
	void testMovietime2h() {
		 var movie2h = movies.stream()
		.filter((Movie m) -> m.getDuration() >= 120)
		//.forEach(System.out::println);
		.count(); 
		System.out.println("Il y a " + movie2h + " films qui dure plus de 2h");	
	}
	
	@Test
	void testLongestTitle() {
			var longtitle = movies.stream()
				.map(Movie:: getTitle)
				.mapToInt(String::length)
				.max()
				.getAsInt();
			System.out.println("La plus grande longeur de caract�re pour un film est de " + longtitle + " caract�res");
			movies.stream()
				.map(Movie::getTitle)
				.filter(t -> t.length() == longtitle)
				.collect(Collectors.toList());
			System.out.println("Films au titre le + long: " + longtitle);
				
	}
	
	@Test
	void nbMovieByYear() {
		 var res = movies.stream()
			.collect(Collectors.groupingBy(Movie::getYear, Collectors.counting()));
		System.out.println(res);	
	}
	
	
	@Test
	void nbMovieByDirector() {
		var movieWithDirector = movies.stream()
				.filter(m -> (Objects.nonNull(m.getDirector())))
	     		.collect(Collectors.groupingBy(Movie::getDirector, Collectors.counting()));
		System.out.println(movieWithDirector);
		var filmographie = movies.stream()
				.filter(m -> (Objects.nonNull(m.getDirector())))
 	     		.collect(Collectors.groupingBy(Movie::getDirector, 
 	     				Collectors.toCollection(
 	     				() -> new TreeSet<>(Comparator.comparing(Movie::getYear,
 	     						Comparator.reverseOrder()))
 	     				)));
		System.out.println(filmographie);
	}
	
	@Test
	void personsByDecade() {
		var res = persons.stream()
			.filter(p -> Objects.nonNull(p.getBirthdate()))	
			.collect(Collectors.groupingBy(p -> p.getBirthdate().getYear() / 10));
		System.out.println(res);
	}
	
	
	@Test
	void testParasite() {
		movies.stream()
			.filter(m -> m.getTitle().equals("Parasite"))
			.findFirst()
			.ifPresent(System.out::println);
	}
	
	@Test
	void testParasite2() {
		var movie = movies.get(1);
		var ac = movie.streamActors()
			.map(p -> p.getName())
			.collect(Collectors.joining(", "));
		System.out.println("Acteur principaux de Parasite: " + ac);
		
	}
	
	@Test
	void testParasiteIterable() {
		var movie = movies.get(1);
		for (var it = movie.interatorActor(); it.hasNext();) { //iterator parcours la "boite" des acteurs
			var actor = it.next();
			System.out.println(actor);
		}
		
	}
		
	
	
	
	
}
