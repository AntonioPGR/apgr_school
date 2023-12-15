package school.pachecos.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import school.pachecos.api.users.UserEntity;
import school.pachecos.api.users.UserRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String SECRET;
	@Value("${api.security.token.issuer}")
	private String ISSUER;
	@Value("${api.security.token.expiration_time}")
	private int EXPIRATION_TIME;
	@Autowired
	UserRepository user_repository;

	public String createToken(UserEntity user){
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			return JWT.create()
					.withIssuer(ISSUER)
					.withExpiresAt(getExpiresAt())
					.withSubject(user.getUsername())
					.sign(algorithm);
		} catch (JWTCreationException exception){
			return null;
		}
	}

	private Instant getExpiresAt(){
		return LocalDateTime.now().plusDays(EXPIRATION_TIME).toInstant(ZoneOffset.of("-03:00"));
	}

	public String getTokenSubject(String token){
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			return JWT.require(algorithm)
					.withIssuer(ISSUER)
					.build()
					.verify(token)
					.getSubject();
		} catch (JWTVerificationException exception){
			return null;
		}
	}

	public String getToken(HttpServletRequest request){
		String token = request.getHeader("Authorization");
		if(token == null){
			return null;
		}
		return token.replace("Bearer", "").trim();
	}

	public UserDetails gerUserFromToken(String token){
		String token_subject = getTokenSubject(token);
		return user_repository.findByEmail(token_subject);
	}

}
