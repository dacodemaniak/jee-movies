package cinema.dao;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cinema.persistance.entity.Account;
import cinema.persistance.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
		System.out.println("Inject user Repository");
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Objects.requireNonNull(username);
		
		System.out.println("Load user by username : " + username);
		
		Account account = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
		System.out.println("Account found with roles : " + account.toString());
		
		return account;
	}
	
}
