package cinema.controller;

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

import cinema.dto.FullMovie;
import cinema.dto.LightMovie;
import cinema.service.IMovieService;


@RestController
@RequestMapping("/api/movie")
public class MovieController {

	@Autowired(required = true)
	IMovieService movieService;

	@GetMapping
	@ResponseBody
	public List<LightMovie> allMovies(){
		return movieService.getAllMovies();
	}

	@GetMapping(value="/{id}")
	@ResponseBody
	public Optional<FullMovie> singleMovie(@PathVariable("id") Integer id) {
		return movieService.getMovieById(id);
	}

	@GetMapping(value="/byTitle")
	@ResponseBody
	public Set<LightMovie> movieByTitle(@RequestParam("t") String title) {
		return movieService.getMovieByTitle(title);
	}

	@GetMapping(value="/byYear")
	@ResponseBody
	public Set<LightMovie> movieByYear(@RequestParam("s") int year) {
		return movieService.getMovieByYear(year);
	}

	@GetMapping(value="/byYearBetween")
	@ResponseBody
	public Set<LightMovie> movieByYearBetween(@RequestParam("s") int year, @RequestParam("e") int year_end) {
		return movieService.getMovieByYearBetween(year, year_end);
	}

	/**
	 * Method: Post
	 */

	@PostMapping("/addMovie")
	public @ResponseBody FullMovie addMovie(@RequestBody FullMovie movie) {
		return movieService.addMovie(movie);
	}

	@PutMapping("/modify")
	public @ResponseBody Optional<FullMovie> mofiyMovie(@RequestBody FullMovie movie) {
		return movieService.modifyMovie(movie);
	}


	@PutMapping("/addActor")
	public Optional<FullMovie> addActorToMovie(@RequestParam("m") int movieId, @RequestParam("a") int actorId) {
		return movieService.addActorToMovie(movieId, actorId);
	}

	/**
	 * Delete
	 */

	@DeleteMapping("/deleteMovie/{id}")
	@ResponseBody
	public FullMovie deleteMovie(@PathVariable("id") int id_movie) {
		return movieService.deleteMovie(id_movie);
	}

}
