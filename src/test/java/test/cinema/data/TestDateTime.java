package test.cinema.data;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.stream;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;

class TestDateTime {

	@Test
	void testParselFrenchDate() {
		String dateStr = "15/01/2020";
		LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("D/M/y") );
		System.out.println(date);
		assertAll( //on demande � java de v�rifier que la date correspond bien
			() -> assertEquals(15, date.getDayOfMonth(), "day"),  
			() -> assertEquals(1, date.getMonthValue(), "month"), //ajout du message month, day etc pour identifier ou est l'erreur
			() -> assertEquals(2020, date.getYear(), "year"));
		
		
	}
	
	@Test
	void testFormatDate() {
		LocalDate date = LocalDate.now();
		var formats = List.of(
				DateTimeFormatter.ofPattern("dd/MM/yyyy"),
				DateTimeFormatter.ofPattern("eeee dd MMMM yyyy"),
				DateTimeFormatter.ofPattern("eeee dd MMMM yyyy",
				Locale.US),
				DateTimeFormatter.ofPattern("eeee dd MMMM yyyy",
				new Locale("es","es")),
				DateTimeFormatter.ofPattern("eeee dd MMMM yyyy",
				new Locale("lv","lv")),
				DateTimeFormatter.ofPattern("eeee dd MMMM yyyy",
						new Locale("ru","ru")),
				DateTimeFormatter.ofPattern("eeee dd MMMM yyyy",
				new Locale("ko","ko")));
		formats.forEach(f -> System.out.println(date.format(f)));

		
	}
	
	
	@Test
		void testPetitTourEnInde () {
			var date = LocalDate.now();
			Arrays.stream(Locale.getAvailableLocales())
				.filter(l -> l.getCountry().equals("IN"))
				.map(l -> date.format(
						DateTimeFormatter.ofPattern("eeee dd MMMM yyyy", l)))
				.forEach(System.out::println);
		}
	
	
	@Test
	void testTourDuMonde() {
		var dtHere = LocalDateTime.now();
		System.out.println("Here :"+ dtHere);
		var DtMI = LocalDateTime.now(ZoneId.of("Pacific/Midway"));
		System.out.println("Midway: " + DtMI);
		var DtNZ = LocalDateTime.now(ZoneId.of("Pacific/Auckland"));
		System.out.println("Auckland: " + DtNZ);
		var DtNY = LocalDateTime.now(ZoneId.of("America/New_York"));
		System.out.println("New York: " + DtNY);
		//System.out.println(ZoneId.SHORT_IDS);
		var fmt = DateTimeFormatter.ofPattern("hh:mm:ss dd/MM/yyyy");
		System.out.println("A Midway c'est : " + DtMI.format(fmt));
		System.out.println("A Auckland c'est : " + DtNZ.format(fmt));
	}
			
}
	
	
	

