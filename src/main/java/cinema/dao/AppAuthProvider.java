package cinema.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import cinema.persistance.entity.Account;

public class AppAuthProvider extends DaoAuthenticationProvider {
	@Autowired
	UserService userDetailService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
		
		String name = auth.getName();
		
		String password = auth.getCredentials().toString();
		
		UserDetails user = userDetailService.loadUserByUsername(name);
		
		if (user == null) {
			throw new BadCredentialsException("Username or password doesn't match for " + auth.getPrincipal());
		}
		System.out.println("Some user was found");
		
		Account authenticateUser = (Account) auth.getPrincipal();
		System.out.println("Account : " + authenticateUser.getLastName());
		
		return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
}
