package com.virtualart.authentication.tokengeneration;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.virtualart.authentication.entity.SignUpModel;
import com.virtualart.authentication.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Component
public class GenerateJwtToken {
	
	@Value("${secret.key}")
	private String jwtsecret;
	@Autowired
	private UserRepository userRepository;
	public String generateToken(String email)
	{
		SignUpModel signupModel = userRepository.findByEmail(email).orElse(new SignUpModel());
		String lastname = signupModel.getLastName();
		String userRole = signupModel.getRole();
		Map<String,String> claims = new HashMap<>();
		claims.put("username",lastname);
		claims.put("role", userRole);
		return createToken(claims,lastname);
	}
	private String createToken(Map<String, String> claims, String username) {
		return Jwts.builder()
				.setClaims(claims) 
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*10))
				.signWith(getsignkey(),SignatureAlgorithm.HS256)
				.compact();
	}
	Key getsignkey() {
		byte[] keys = Decoders.BASE64.decode(jwtsecret);
		return Keys.hmacShaKeyFor(keys);
	}
}
