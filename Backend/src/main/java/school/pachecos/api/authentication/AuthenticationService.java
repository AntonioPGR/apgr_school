package school.pachecos.api.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import school.pachecos.api.authentication.dtos.LoginDTO;
import school.pachecos.api.users.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UserRepository user_repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return user_repository.findByEmail(email);
	}

}
