package cinema.persistence.entity.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cinema.persistance.entity.Movie;
import cinema.persistance.entity.Person;
import cinema.persistance.repository.MovieRepository;

@DataJpaTest //h2 par défaut
@AutoConfigureTestDatabase(replace = Replace.NONE) //on dit de pas remplacer par h2
class TestMovie {
	
	
	@Autowired
	MovieRepository repoMovie; //interface qui permet de créer une class 
	
	@Autowired
	EntityManager entityManager;

	@Test
	void testSave() {
		Movie movie = new Movie("Joker", 2019);
		//hbn.persist(movie); //sauvegarder le film dans la base de donnée
		repoMovie.save(movie);
		var id = movie.getId_movie();
		System.out.println("Id new movie " + movie.getId_movie());
		assertNotNull(id);
	}
	
	
	@Test
	void testSaveWithDirector() {
		//given
		Person person = new Person ("Todd Phillips", LocalDate.of(1970, 12, 20));
		Movie movie = new Movie("Joker", 2019, 165, person);
		entityManager.persist(person);
		//when
		repoMovie.save(movie);
		//then
		System.out.println(person);
		System.out.println(movie);
	}
	
	@Test
	void testSelectAll() {
		//given
		List<Movie> data = List.of(
				 new Movie("Joker", 2019),
				 new Movie("Avengers: Endgame", 2019, 181),
				 new Movie("Captain Marvel", 2014, 123),
				 new Movie("Avengers: L'ère d'Ultron", 2015, 142),
				 new Movie("Parasite", 2019, 132),		 		 
				 new Movie("interstellar", 2014)	
				);
		data.forEach(entityManager::persist);
		//when
		var dataRead = repoMovie.findAll();
		//then
		dataRead.forEach(System.out::println);
		assertEquals(data.size(), dataRead.size());
		assertTrue(dataRead.stream()
			.map(Movie::getTitle)
			.allMatch(tr -> data.stream()  //tous vrai
					.map(Movie::getTitle)
					.anyMatch(th -> th.equals(tr)) // au moins un vrai
					));
	}
	
	@Test
	void testFindById() {
		//given
		Movie movie = new Movie("Joker", 2019);
		entityManager.persist(movie);
		var id = movie.getId_movie();
		//when
		var movieReadOpt = repoMovie.findById(id);
		System.out.println(movieReadOpt);
		assertAll(
		() -> assertTrue(movieReadOpt.isPresent()),
		() -> assertEquals(movie.getTitle(), movieReadOpt.get().getTitle())
		);
		
	}
	
	@Test
	void testFindByTitle() {
		//given
		String title = "Le Roi Lion";
		List<Movie> data = List.of(
				 new Movie("Joker", 2019),
				 new Movie(title, 2019),
				 new Movie(title, 1994),
				 new Movie("Captain Marvel", 2014, 123),
				 new Movie("Parasite", 2019, 132),		 		 
				 new Movie("interstellar", 2014)	
				);
		data.forEach(entityManager::persist);
		//when
		var dataRead = repoMovie.findByTitle(title);
		System.out.println(dataRead);

	}
	
	@Test
	void testFindByYear() {
		//given
		int year = 2019;
		int year2 = 2010;
		int Staryear = 1995;
		int LastYear = 2015;
		List<Movie> data = List.of(
				 new Movie("Joker", 2019),
				 new Movie("Le Roi Lion", 2019),
				 new Movie("Seven", Staryear),
				 new Movie("Mad Max : Fury Road",LastYear),
				 new Movie("Parasite", 2019, 132),		 		 
				 new Movie("interstellar", 2014),
				 new Movie("Grand Torino", 2008)
				);
		data.forEach(entityManager::persist);
		//when
		var dataRead= repoMovie.findByYearBetween(Staryear, LastYear);
		System.out.println(dataRead);
		assertAll(
				() -> assertEquals(4,dataRead.size()),
				() -> assertTrue(dataRead.stream()
						.mapToInt(Movie::getYear)
						.allMatch(y -> (y>= Staryear) && (y<= LastYear))));
		
	}
	
	@Test
	void testFindByTitleAndYear() {
		//given
		String title = "Le Roi Lion";
		int year = 1994;
		List<Movie> data = List.of(
				 new Movie("Forest Gump", year),
				 new Movie(title, 2019),
				 new Movie(title, year),	 		 
				 new Movie("interstellar", 2014)	
				);
		data.forEach(entityManager::persist);
		//when
		var dataRead = repoMovie.findByTitleAndYear(title,year);
		System.out.println(dataRead);

	}
	
	@Test
	void testFindByActorsNameEndingWith() {
	
		var madMax = new Movie("Mad Max",1978);
		var roiLion = new Movie("Le Roi Lion",1994);
		var armeFatale = new Movie("L'Arme Fatale",1987);
		var movies = List.of(roiLion,madMax,armeFatale);
		movies.forEach(entityManager::persist);
		var melGibson = new Person ("Mel Gibson");
		var whoopi = new Person ("Whoopi Golberg");
		var danny = new Person("Danny Glover");
		entityManager.persist(melGibson);
		entityManager.persist(whoopi);
		entityManager.persist(danny);
		roiLion.getActors().add(whoopi);
		madMax.getActors().add(melGibson);
		Collections.addAll(armeFatale.getActors(), melGibson,danny);
		entityManager.flush();
		//when
		var movieWithMe1 = repoMovie.findByActorsNameEndingWith("Gibson");
		//then
		assertAll(
		() -> assertTrue(movieWithMe1.contains(madMax)),
		() -> assertTrue(movieWithMe1.contains(armeFatale)),
		() -> assertFalse(movieWithMe1.contains(roiLion))
		);
		System.out.println(movieWithMe1);
		
		
		
		
	}

}
