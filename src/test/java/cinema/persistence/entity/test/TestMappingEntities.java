package cinema.persistence.entity.test;

/**
 * this not a unit test case
 */

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import cinema.persistance.entity.Movie;
import cinema.persistance.entity.Person;
import cinema.persistance.repository.MovieRepository;
import cinema.persistance.repository.PersonRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class TestMappingEntities {
	
	@Autowired
	PersonRepository repoPersons;
	
	@Autowired
	MovieRepository repoMovies;
	
	
	@Rollback(false)
	@Test
	void testSaveData() {
			var joaq = new Person("Joaquin Phoenix", LocalDate.of(1974, 10, 28));	
			var gege = new Person("Gerard Darmon", LocalDate.of(1948, 2, 29));	
			var todd = new Person("Todd Phillips", LocalDate.of(1970, 12, 20));
			var clint = new Person("Clint Eastwood", LocalDate.of(1930, 5, 31)); 	
			var brad = new Person("Bradley Cooper", LocalDate.of(1975, 1, 5));	
			var gene = new Person("Gene Hackman", LocalDate.of(1930, 1, 30));
			var morgan = new Person("Morgan Freeman", LocalDate.of(1937, 6, 1));
			
			var persons= List.of(joaq,gege,todd,clint,brad,gene,morgan);
			persons.forEach(repoPersons::save);
			
			
		
			var joker = new Movie("Joker", 2019, 165, todd);			 
			var parasite = new Movie("Parasite", 2019, 132);		 		 
			var interstellar = new Movie("interstellar", 2014);				 
			var GT = new Movie("Gran Torino", 2008, 116, clint);     
			var impitoyable = new Movie("Impitoyable", 1992, 131, clint);		 	 
			var AS = new Movie("American Sniper", 2014, 133, clint); 
			var VBT = new Movie("Very Bad Trip", 2009, 100, todd); 	 
			var Avengers = new Movie("Avengers", 2012, 145);
			var AEndgame = new Movie("Avengers: Endgame", 2019, 181);
			var CaptainM = new Movie("Captain Marvel", 2014, 123);
			var AUltron = new Movie("Avengers: L'ère d'Ultron", 2015, 142);
			var AInfinity = new Movie("Avengers: Infinity War", 2018, 149);
			var nightof = new Movie("Night of the Day of the Dawn of the Son of the Bride of the Return of the Revenge of the Terror of the Attack of the Evil, Mutant, Hellbound, Flesh-Eating Subhumanoid Zombified Living Dead, Part 3", 2005);
					  	
			var movies = List.of(joker,parasite,interstellar,GT,impitoyable,AS,VBT,Avengers,AEndgame);
			movies.forEach(repoMovies::save);
	}

}
