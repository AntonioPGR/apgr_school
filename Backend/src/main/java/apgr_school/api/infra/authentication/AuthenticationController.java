package apgr_school.api.infra.authentication;

import apgr_school.api.infra.security.DTO.TokenResponseDTO;
import apgr_school.api.infra.authentication.DTO.UserAuthenticateDTO;
import apgr_school.api.infra.security.TokenService;
import apgr_school.api.models.users.User;
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
	@Autowired
	private TokenService token_service;

	@PostMapping
	public ResponseEntity POST(@RequestBody @Valid UserAuthenticateDTO user_data){
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user_data.email(), user_data.password());
		Authentication authentication = auth_manager.authenticate(token);
		String generated_token = token_service.generateUserToken(user_data);
		TokenResponseDTO response_dto = new TokenResponseDTO(generated_token);
		return ResponseEntity.ok(response_dto);
	}

}
