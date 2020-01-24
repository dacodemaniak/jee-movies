package test.cinema.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

class TestMap {

	@Test 	//map type abstract
	void test() {
		Map<Integer,Integer> nbMovieByYear = new TreeMap<>() ; 
		//implementation en donnant un ordre au clé avec treemap
		nbMovieByYear.put(2019, 23);
		nbMovieByYear.put(2017, 12);
		nbMovieByYear.put(2014, 7);
		nbMovieByYear.put(1992, 22);
		System.out.println(nbMovieByYear);
		nbMovieByYear.put(2014, 13); //replace
		System.out.println(nbMovieByYear);
		System.out.println("En 2017 : " + nbMovieByYear.get(2017));
		for (var year: nbMovieByYear.keySet()) { // key is unique -> keySet à la place de keys
			System.out.println("* year : " + year);
		}
		for (var year: nbMovieByYear.values()) {
			System.out.println("* nb movies: " + year);
		}
		
	}

}
