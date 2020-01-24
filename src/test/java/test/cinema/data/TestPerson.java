package test.cinema.data;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import cinema.data.Person;

class TestPerson {

	@Test
	void testCreate() {
		Person jp = new Person ("Joaquin Phoenix", LocalDate.of(1974, 10, 28));
		Person gd = new Person ("Gerard Darmon", LocalDate.of(1948, 02, 29));
		Person tp = new Person ("Todd Phillips");
		System.out.println(jp + " : " + jp.getAge());
		System.out.println(gd + " : " + gd.getAge());
		System.out.println(tp + " : " + tp.getAge());
	}

//	 @Test  // 2019 n'est pas une année bix
//	 void testBirthdatefeburary () {
//		 LocalDate birthdate = LocalDate.of(2019, 02, 29);
//	 }
}
