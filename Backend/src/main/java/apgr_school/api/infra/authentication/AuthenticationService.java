package apgr_school.api.infra.authentication;

import apgr_school.api.models.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService  implements UserDetailsService {

	@Autowired
	private UserRepository user_repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return user_repository.findByEmail(username);
	}

}
