package school.pachecos.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import school.pachecos.api.users.UserEntity;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;

	public String createToken(UserEntity user){
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.create()
					.withIssuer("API Pacheco's School")
					.withExpiresAt(getExpiresAt())
					.withSubject(user.getEmail())
					.sign(algorithm);
		} catch (JWTCreationException exception){
			return null;
		}
	}

	private Instant getExpiresAt(){
		return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("-03:00"));
	}

	/*public Boolean verifyToken(){
		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJpc3MiOiJhdXRoMCJ9.AbIJTDMFc7yUa5MhvcP03nJPyCPzZtQcGEp-zWfOkEE";
		DecodedJWT decodedJWT;
		try {
			Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
			JWTVerifier verifier = JWT.require(algorithm)
					// specify an specific claim validations
					.withIssuer("auth0")
					// reusable verifier instance
					.build();

			decodedJWT = verifier.verify(token);
		} catch (JWTVerificationException exception){
			return null;
		}
	}*/

}
