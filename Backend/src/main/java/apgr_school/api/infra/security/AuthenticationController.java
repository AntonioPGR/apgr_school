package apgr_school.api.infra.security;

import apgr_school.api.infra.security.DTO.UserAuthenticateDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager auth_manager;

	@PostMapping
	public ResponseEntity POST(@RequestBody @Valid UserAuthenticateDTO user_data){
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user_data.email(), user_data.password());
		Authentication authentication = auth_manager.authenticate(token);

		return ResponseEntity.ok().build();
	}

}
