package cinema.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	@Autowired
	IMovieService movieService;
	

	@GetMapping
	@ResponseBody //mettre la demande dans le corps 
	public List<Movie> allmovies() {
		return movieService.getAllMovies();
	}
	
	@GetMapping("/{idMovie}")
	@ResponseBody
	public Optional<Movie> movieById(@PathVariable("") int idMovie) { //pathvariable suis le chemin indiqué dans GetMapping
		return movieService.getMovieById(idMovie);
	}
	
	@GetMapping("/byTitle")
	@ResponseBody
	public Set<Movie> movieByTitle(@RequestParam ("t") String partialTitle) { 
		return movieService.getMovieByPartialTitle(partialTitle);
	}
		
	
	@GetMapping("/byDirector")
	@ResponseBody
	public Set<Movie> findByDirector(@RequestParam ("d") int idDirector) {
		return movieService.getMoviesByDirector(idDirector);
	}
	
	
	@GetMapping("/byActor")
	@ResponseBody
	public Set<Movie> movieByActor(@RequestParam ("a") int idActor) {
		return movieService.getMovieByActor(idActor);
	}

}
	
//	@PostMapping
//	@ResponseBody //retransformer l'info en json pour etre lu en front
//	public Movie addMovie(@RequestBody Movie movie) {
//		Movie movieSaved = movieRepository.save(movie);
//		movieRepository.flush();
//		return movieSaved;
//	}
//	
//	@PutMapping("/setDirector")
//	@ResponseBody
//	public Optional<Movie> setDirector(@RequestParam ("d") int idDirector, @RequestParam ("m") int idMovie) {
//		var directorOpt = personRepository.findById(idDirector);
//		var movieOpt = movieRepository.findById(idMovie);
//		if (movieOpt.isPresent() && directorOpt.isPresent()) {
//			movieOpt.get().setDirector(directorOpt.get());
//			movieRepository.flush();
//		}
//		return movieOpt;
//	}
//	
//	@PutMapping("/addActor")
//	@ResponseBody
//	public Optional<Movie> addActor(@RequestParam ("a") int idActor, @RequestParam ("m") int idMovie) {
//		var movieOpt = movieRepository.findById(idMovie);
//		var actorOpt = personRepository.findById(idActor);
//		
//		if(movieOpt.isPresent() && actorOpt.isPresent()) {
//			movieOpt.get().getActors().add(actorOpt.get());
//			movieRepository.flush();
//		}
//		return movieOpt;
//	}
//	
//	@PutMapping("/modify")
//	@ResponseBody
//	public Optional<Movie> modifyMovie(@RequestBody Movie movie) {
//		var optMovie = movieRepository.findById(movie.getId_movie());
//		// TODO : anywhere else
//		optMovie.ifPresent(m -> {
//			m.setTitle(movie.getTitle());
//			m.setYear(movie.getYear());
//			m.setDuration(movie.getDuration());
//			m.setDirector(movie.getDirector());	
//			movieRepository.flush();
//		});			
//		//		
//		return optMovie;
//	}
//
//	
//	@DeleteMapping("/{id}")
//	@ResponseBody
//	public Optional<Movie> deleteMovie(@PathVariable("id") int idMovie) {
//		var movieToDelete = movieRepository.findById(idMovie);
//		movieToDelete.ifPresent(m -> {
//		movieRepository.delete(m);
//		movieRepository.flush();		
//		});
//		return movieToDelete;
//	}
//}
