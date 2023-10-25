package apgr_school.api.controllers;

import apgr_school.api.infra.authentication.DTO.UserAuthenticateDTO;
import apgr_school.api.infra.token.DTO.TokenResponseDTO;
import apgr_school.api.infra.token.TokenService;
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
@RequestMapping("authenticate/")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager auth_manager;
	@Autowired
	private TokenService token_service;

	@PostMapping("login/")
	public ResponseEntity<TokenResponseDTO> POST(@RequestBody @Valid UserAuthenticateDTO auth_dto){
		Authentication auth = authenticateUser(auth_dto);
		TokenResponseDTO token_dto = generateUserToken((User) auth.getPrincipal());
		return ResponseEntity.ok().body(token_dto);
	}

	private Authentication authenticateUser(UserAuthenticateDTO auth_dto){
		UsernamePasswordAuthenticationToken username_password_auth_token = new UsernamePasswordAuthenticationToken(auth_dto.email(), auth_dto.password());
		return auth_manager.authenticate(username_password_auth_token);
	}

	private TokenResponseDTO generateUserToken(User user) {
		String token = token_service.generateToken(user);
		return new TokenResponseDTO(token);
	}

}
