package cinema.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cinema.persistance.entity.Movie;
import cinema.service.IMovieService;


@RestController
@RequestMapping("/api/movie")
public class MovieController {

	@Autowired(required = true)
	IMovieService movieService;

	@GetMapping
	@ResponseBody
	public List<Movie> allMovies(){
		return movieService.getAllMovies();
	}

	@GetMapping(value="/{id}")
	@ResponseBody
	public Optional<Movie> singleMovie(@PathVariable("id") Integer id) {
		return movieService.getMovieById(id);
	}

	@GetMapping(value="/byTitle")
	@ResponseBody
	public Set<Movie> movieByTitle(@RequestParam("t") String title) {
		return movieService.getMovieByTitle(title);
	}

	@GetMapping(value="/byYear")
	@ResponseBody
	public Set<Movie> movieByYear(@RequestParam("s") int year) {
		return movieService.getMovieByYear(year);
	}
	
	@GetMapping(value="/byYearBetween")
	@ResponseBody
	public Set<Movie> movieByYearBetween(@RequestParam("s") int year, @RequestParam("e") int year_end) {
		return movieService.getMovieByYearBetween(year, year_end);
	}

	@GetMapping("/findByDirector")
	@ResponseBody
	public Set<Movie> movieByDirectore(@RequestParam("d") int idDirector) {	
		return movieService.getMoviesByDirector(idDirector);
	}

	@GetMapping("/findByActor")
	@ResponseBody
	public Set<Movie> movieByActor(@RequestParam("a") int idActor) {		
		return movieService.getMovieByActor(idActor);
	}
	
	/**
	 * Method: Post
	 */
	
	@PostMapping("/addMovie")
	public @ResponseBody Movie addMovie(@RequestBody Movie movie) {
		return movieService.addMovie(movie);
	}

	
//	@PutMapping("/modify")
//	public @ResponseBody Optional<Movie> mofiyMovie(@RequestBody Movie movie) {
//		var optMovie = movieService.findById(movie.getId());
//		
//		optMovie.ifPresent(m -> {
//			m.setTitle(movie.getTitle());
//			m.setYear(movie.getYear());
//			m.setDuration(movie.getDuration());
//			m.setDirector(movie.getDirector());
//		});	
//		movieService.flush();
//		return optMovie;
//	}
		
		
	//	
	//	@PutMapping("/addActor")
	//	public Optional<Movie> addActorToMovie(@RequestParam("m") int movieId, @RequestParam("a") int actorId) {
	//		//alita #4/ actor #12
	//		var movieOpt = movieService.findById(movieId);
	//		var actorOpt = personRepository.findById(actorId);
	//		
	//		if (movieOpt.isPresent() && actorOpt.isPresent()) {
	//			movieOpt.get().getActors().add(actorOpt.get());
	//			movieService.flush();
	//		}
	//		
	//		return movieOpt;
	//	}
	//
	//	@PutMapping("/addDirector")
	//	public Optional<Movie> addDirectorToMovie(@RequestParam("m") int movieId, @RequestParam("d") int directorId) {
	//		var movieOpt = movieService.findById(movieId);
	//		var directorOpt = personRepository.findById(directorId);
	//		
	//		if (movieOpt.isPresent() && directorOpt.isPresent() ) {
	//			movieOpt.get().setDirector(directorOpt.get());
	//			movieService.flush();
	//		}
	//		return movieOpt;
	//	}
	//	
	//	
	//	@DeleteMapping("/{id}")
	//	@ResponseBody
	//	public Optional<Movie> deleteMovie(@PathVariable("id") int id) {
	//		var delMovie = movieService.findById(id);
	//		delMovie.ifPresent(m -> {
	//			movieRepository.delete(m);
	//			movieRepository.flush();
	//		});
	//		return delMovie;
	//	}
	//	
	//	
}
