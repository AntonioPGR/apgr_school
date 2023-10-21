package apgr_school.api.infra.security;

import apgr_school.api.infra.authentication.DTO.UserAuthenticateDTO;
import apgr_school.api.models.users.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

	@Value("api.security.token.secret")
	private String secret_key;

	public String generateUserToken(UserAuthenticateDTO user){
		Algorithm algorithm = Algorithm.HMAC256(secret_key);
		return JWT.create()
				.withIssuer("API Pacheco's School")
				.withSubject(user.email())
				.withExpiresAt(createExpireData())
				.sign(algorithm);
	}

	private Instant createExpireData() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

}
