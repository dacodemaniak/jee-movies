package cinema.persistence.entity.test;

/**
 * this not a unit test case
 */

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.mapping.Array;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import cinema.persistance.entity.Actor;
import cinema.persistance.entity.Movie;
import cinema.persistance.entity.Person;
import cinema.persistance.repository.ActorRepository;
import cinema.persistance.repository.MovieRepository;
import cinema.persistance.repository.PersonRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class TestMappingEntities {
	
	@Autowired
	PersonRepository repoPersons;
	
	@Autowired
	MovieRepository repoMovies;
	
	@Autowired
	ActorRepository actorRepo;
	
	@Autowired
	EntityManager em;
	
	
	@Rollback(false)
	@Test
	void testSaveData() {
			
			var joaq = new Person("Joaquin Phoenix", LocalDate.of(1974, 10, 28), List.of("American") );	
			var gege = new Person("Gerard Darmon", LocalDate.of(1948, 2, 29),List.of("French","Morocco"));	
			var todd = new Person("Todd Phillips", LocalDate.of(1970, 12, 20),List.of("American"));
			var clint = new Person("Clint Eastwood", LocalDate.of(1930, 5, 31),List.of("American")); 	
			var brad = new Person("Bradley Cooper", LocalDate.of(1975, 1, 5),List.of("American"));	
			var gene = new Person("Gene Hackman", LocalDate.of(1930, 1, 30),List.of("American"));
			var morgan = new Person("Morgan Freeman", LocalDate.of(1937, 6, 1),List.of("American"));
			var robert = new Person("Robert Downey Jr", LocalDate.of(1965, 4, 4),List.of("American"));		
			var naomi = new Person("Naomi Watts", LocalDate.of(1968, 9, 28),List.of("British"));
			var emma = new Person("Emma Stone", LocalDate.of(1988, 11, 6),List.of("American")); 	
			var ryan = new Person("Ryan Gosling", LocalDate.of(1980, 11, 12),List.of("Canadian"));
			var joss = new Person ("Joss Whedon", LocalDate.of(1964, 6, 23),List.of("American"));
			var jack = new Person ("Jack Black", LocalDate.of(1969, 8, 28),List.of("American"));
			var chris = new Person ("Christopher Nolan", LocalDate.of(1969, 8, 28),List.of("American","British"));
			var peter = new Person("Peter Jackson", LocalDate.of(1961,10,31),List.of("New Zealander"));
			
			var persons= List.of(joaq,gege,todd,clint,brad,gene,morgan,robert,naomi,emma,ryan,joss,jack,chris,peter);
			 
			persons.forEach(repoPersons::save);
			
			Movie joker = new Movie("Joker", 2019, 165, List.of("Drama","Thriller"),todd);			 
			Movie parasite = new Movie("Parasite", 2019, 132,List.of("Comedy","Drama","Thriller"));		 		 
			Movie interstellar = new Movie("interstellar", 2014,157,List.of("Adventure","Drama","Sci-Fi"),chris);				 
			Movie GT = new Movie("Gran Torino", 2008, 116,List.of("Drama","Thriller"),clint);     
			Movie impitoyable = new Movie("Impitoyable", 1992, 131,List.of("Drama","Western"),clint);		 	 
			Movie AS = new Movie("American Sniper", 2014, 133, List.of("Action",""),clint); 
			Movie VBT = new Movie("Very Bad Trip", 2009, 100,List.of("Comedy") ,todd); 	 
			Movie Avengers = new Movie("Avengers", 2012, 145,List.of("Action","Adventure","Sci-Fi"),joss);
			Movie AEndgame = new Movie("Avengers: Endgame", 2019, 181,List.of("Action","Adventure","Sci-Fi"));
			Movie mule = new Movie("La Mule",2018,116,List.of("Crime","Drama","Thriller"),clint); 
			Movie tonnerre = new Movie("Tonnerre sous les tropiques",2008,107,List.of("Comedy","War"));
			Movie birdman = new Movie("BirdMan",2014,119,List.of("Comedy","Drama"));
			Movie kingKong = new Movie("King Kong",2005,180,List.of("Action","Adventure","Drama"),peter);				 
			Movie Lala = new Movie("LaLaland",2016,128,List.of("Comedy","Drama","Music"));     
			
			var movies = List.of(joker,parasite,interstellar,GT,impitoyable,AS,VBT,Avengers,AEndgame,mule,tonnerre,birdman,kingKong,Lala);
			movies.forEach(repoMovies::save);
	}
	
	@Rollback(false)
	@Test
	void testAddMovie() {			
				
		var actor = actorRepo.findAll().stream().map(a -> a.getPerson().getIdPerson() == 21);
		System.out.println(actor.toString());
	}
	
	@Rollback(false)
	@Test
	void testAddPersons() {
		var gege = new Person("Gerard Darmon", LocalDate.of(1948, 2, 29),List.of("French","Morocco"));
		repoPersons.saveAndFlush(gege);
	}

}



