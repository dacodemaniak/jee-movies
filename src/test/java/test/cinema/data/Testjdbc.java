package test.cinema.data;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import cinema.data.Movie;
import cinema.data.Person;

class Testjdbc {
	static DataSource ds; // une seule base de donnée
	
	@BeforeAll
	static void initDatasource() {
		PGSimpleDataSource pgds = new PGSimpleDataSource();
		String host = "localhost";
		int port = 5432;
		String user = "cinema";
		String password = "password";
		String dbname = "postgres";
		pgds.setURL(
				"jdbc:postgresql://"  
				+ host +":" + port + "/" + dbname);
		pgds.setUser(user);
		pgds.setPassword(password);
		ds = pgds;
//		System.out.println(pgds.getURL());
	}
 
	@Test
	void test() throws SQLException {
		var listMovie = new TreeSet<Movie>(
				Comparator.comparing(Movie::getTitle).thenComparing(Movie::getYear));
		String sql = "select * from film";
		try (
			Connection conn = ds.getConnection();  //autoclose
			Statement request = conn.createStatement();
			ResultSet res = request.executeQuery(sql);
			) {
			while (res.next()) {
				String title = res.getString("titre");
				int year = res.getInt("annee");
				int duration = res.getInt("duree");
				//add movie
			listMovie.add(new Movie(title, year, duration));
			}
		}//res/request/conn.close(); //in every scenario if possible
		System.out.println(listMovie);
		for (var m:listMovie) {
			System.out.println(m + " de durée " + m.getDuration() + " mn");
		}
		var totalduration = listMovie.stream()
		.mapToInt(Movie::getDuration)
		.sum();
		System.out.println("La durée totale des films est de " + totalduration + " min");
	}
		
	
	
	@Test
	void testLireFilmsFiltre() throws SQLException {  //marche pas et ne pas a faire 
		String sql = "select * from film where duree >= ?"; //? correspond à une requete utilisateur
		var listMovie = new TreeSet<Movie>(
				Comparator.comparing(Movie::getTitle).thenComparing(Movie::getYear));
		int durationThreshold = 120; // requete utilisateur
		//sql += durationThreshold; interdit
		System.out.println(sql);
		try (
				Connection conn = ds.getConnection();
				PreparedStatement request = conn.prepareStatement(sql);
		) {
			request.setInt(1, durationThreshold);
			try (ResultSet res = request.executeQuery()) {
				while(res.next()) {
					String title = res.getString("titre");
					int year = res.getInt("annee");
					int duration = res.getInt("duree");
					listMovie.add(new Movie(title, year, duration));
				}
			}	//res.close()
		}	//request/conn.close()
		System.out.println(listMovie);
		// check all movies with duration >= durationTheshold
		assertAll(listMovie.stream()
			.map(m -> () -> assertTrue(m.getDuration() >= durationThreshold, m.getTitle())));
		
		
		
	}
	
	@Test
	void testLireFilmsInjection() throws SQLException {
		String sql = "select * from film where titre = '";
		String sql2 = "'";
		String title = "Joker' or true or titre='";
		sql+= title +sql2;
		System.out.println(sql);
	}
	
	@Test
	void testLireFilmsInjectionNotAllowed() throws SQLException {
		String sql = "select * from film where titre = ?";
		String title = "Joker' or true or titre='";
		try (
			Connection conn = ds.getConnection();
			PreparedStatement request = conn.prepareStatement(sql);
		) {
			request.setString(1, title);
			try (ResultSet res = request.executeQuery()) {
				while(res.next()) {
					System.out.println(res.getString("titre"));
				}
			}				
		}
	}
	
	
	@Test
	void testAddPerson() throws SQLException {
		var persons = List.of(
				new Person ("Bradley Cooper", LocalDate.of(1975, 1, 5)),
				new Person("Gene Hackman", LocalDate.of(1930, 1, 30)),
				new Person("Morgan Freeman", LocalDate.of(1937, 6, 1))
				);
		
		String sql = "insert into individu (prenom, nom, date_naissance) values (?,?,?)";
		try (
				Connection conn = ds.getConnection();
				PreparedStatement request = conn.prepareStatement(sql);
			) {
               for (var person: persons)	 {
            	  String fullname = person.getName();
            	  var parts = fullname.split(" ");
            	System.out.println(Arrays.toString(parts));
            	request.setString(1, parts[0]);
            	request.setString(2, parts[1]);
            	request.setDate(3, Date.valueOf(person.getBirthdate()));
            	request.executeUpdate();
               }
		    }
	}
	
}
