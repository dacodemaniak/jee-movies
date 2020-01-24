package cinema.persistence.entity.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

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
class TestCinema {
	
	@Autowired
	PersonRepository repoPersons;
	
	@Autowired
	MovieRepository repoMovies;
	
	@Autowired
	EntityManager entityManager;

	
	@Rollback(false)
	@Test
	void testAddDirectorExistingMovie() {
		var movies = repoMovies.findByTitle("interstellar");
		if(movies.size() > 0) {
			var interstellar = movies.stream().findFirst().get();
			var chris = new Person("Christopher Nolan", LocalDate.of(1970,6,30));
			repoPersons.save(chris);
			interstellar.setDirector(chris);
		}	
	}
	

	@Rollback(false)
	@Test
	void testSelectMovieWithDirector() {
		var movies = repoMovies.findByTitle("interstellar");
		if(movies.size() > 0) {
			var interstellar = movies.stream().findFirst().get();
			var director = interstellar.getDirector();
			System.out.println(director);
		}
	}
	
	@Rollback(false)
	@Test
	void testAddNewMovie() {
		var batman = new Movie("The Dark Knight", 2008, 153);
		repoMovies.save(batman);
		var persons = repoPersons.findByName("Christopher Nolan");
		var chris = persons.stream().findFirst().get();
		batman.setDirector(chris);
		//repoMovies.flush(); //
	}

	
	
	@Rollback(false)
	@Test
	void scenarioMovieWithDirector1 (){
		var impitoyable = repoMovies.findByTitle("Impitoyable").stream().findFirst().get();
		var clint = repoPersons.findByName("Clint Eastwood").stream().findFirst().get();
		var gene = repoPersons.findByName("Gene Hackman").stream().findFirst().get();
		impitoyable.setActors(List.of(clint,gene));
		repoMovies.flush();
	}
	
	@Rollback(false)
	@Test
	void scenarioMovieAddActor() {
		var impitoyable = repoMovies.findByTitle("Impitoyable").stream().findFirst().get();
		var morgan = repoPersons.findByName("Morgan Freeman").stream().findFirst().get();
		impitoyable.getActors().add(morgan);
		repoMovies.flush();
	}	
		
	
	@Rollback(false)
	@Test
	void testLazyActors() {
		//read a movie : select the movie + its director
		var impitoyable = repoMovies.findByTitle("Impitoyable").stream().findFirst().get();
		//read actors
		var actors = impitoyable.getActors();
	}

	
//	@Test
//	void scenarioSelectByDirector() {
//		var data1 = repoMovies.findByDirectorNameEndingWith("Eastwood");
//		var nolan = repoPersons.findByName("Christopher Nolan")
//				.stream().findFirst().get();
//		var data2 = repoMovies.findByDirector(nolan);
//		System.out.println(data1);
//		System.out.println(data2);
//	}
//	
}
