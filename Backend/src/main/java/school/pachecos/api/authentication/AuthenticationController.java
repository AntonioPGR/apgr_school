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
	AuthenticationManager authentication_manager;
	@Autowired
	TokenService token_service;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid LoginDTO login_info){
		UsernamePasswordAuthenticationToken auth_info = new UsernamePasswordAuthenticationToken(login_info.email(), login_info.password());
		Authentication authentication = authentication_manager.authenticate(auth_info);
		String jwt_token = token_service.createToken((UserEntity) authentication.getPrincipal());
		LoginResponseDTO response_dto = new LoginResponseDTO(jwt_token);
		return ResponseEntity.ok().body(response_dto);
	}

}
