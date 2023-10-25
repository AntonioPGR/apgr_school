package apgr_school.api.infra.token;

import apgr_school.api.models.users.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

	private final String SECRET_KEY = "pRWD6XtxAR3ddGArFGCZkJORZ8sIbtbWHtTliEDhWSfYFQw1uK9B6EXibrUPAvmu";
	private final String ISSUER = "apgr_school";

	public String generateToken(User user) {
		return JWT.create()
				.withIssuer(ISSUER)
				.withExpiresAt(getExpiresAt())
				.withSubject(user.getEmail())
				.withClaim("id", user.getId())
				.sign(getAlgorithm());
	}

	public String decodeToken(String token){
		return JWT.require(getAlgorithm())
				.withIssuer(ISSUER)
				.build()
				.verify(token)
				.getSubject();
	}

	public String getTokenFromRequest(HttpServletRequest request){
		String complete_token = request.getHeader("Authorization");
		if(complete_token != null){
			complete_token = complete_token.replace("Bearer ", "");
		}
		return complete_token;
	}

	private Instant getExpiresAt() {
		return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
	}

	private Algorithm getAlgorithm(){
		return Algorithm.HMAC256(SECRET_KEY);
	}

}
