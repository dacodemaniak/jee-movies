package cinema.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("administrator".equals(username)) {
			return new User(
					"administrator", 
					"$2y$10$9gWtJegHbedpbbtIlPbvbe8Cx1SGXNJBVIG6rUD6QOhsd0V/VHVmW",
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with this username");
		}
	}

}
