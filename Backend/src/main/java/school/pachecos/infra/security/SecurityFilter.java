package school.pachecos.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	TokenService token_service;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filter_chain) throws ServletException, IOException {
		String token = token_service.getToken(request);
		if(token != null){
			UserDetails user_details = token_service.gerUserFromToken(token);
			authenticate_user(user_details);
		}
		filter_chain.doFilter(request, response);
	}

	private void authenticate_user(UserDetails user_details){
		Authentication authentication = new UsernamePasswordAuthenticationToken(user_details, null, user_details.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
