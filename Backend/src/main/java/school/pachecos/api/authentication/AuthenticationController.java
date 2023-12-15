package school.pachecos.api.authentication;

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
import school.pachecos.api.authentication.dtos.LoginDTO;
import school.pachecos.api.authentication.dtos.LoginResponseDTO;
import school.pachecos.api.users.UserEntity;
import school.pachecos.infra.security.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	TokenService token_service;
	@Autowired
	private AuthenticationManager authentication_manager;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid LoginDTO login_info){
		Authentication auth = authenticateUser(login_info);
		LoginResponseDTO response_dto = generateTokenResponse(auth);
		return ResponseEntity.ok().body(response_dto);
	}

	private Authentication authenticateUser(LoginDTO login_info){
		UsernamePasswordAuthenticationToken auth_info = new UsernamePasswordAuthenticationToken(login_info.email(), login_info.password());
		return authentication_manager.authenticate(auth_info);
	}

	private LoginResponseDTO generateTokenResponse(Authentication auth){
		String jwt_token = token_service.createToken((UserEntity) auth.getPrincipal());
		return new LoginResponseDTO(jwt_token);
	}

}
