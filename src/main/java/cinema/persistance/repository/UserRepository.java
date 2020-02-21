package cinema.persistance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cinema.persistance.entity.Account;


public interface UserRepository extends JpaRepository<Account, Integer> {
	@Query("SELECT a FROM Account a " + 
			" WHERE a.username = ?1")
	Optional<Account> findByUsername(String username);

}
