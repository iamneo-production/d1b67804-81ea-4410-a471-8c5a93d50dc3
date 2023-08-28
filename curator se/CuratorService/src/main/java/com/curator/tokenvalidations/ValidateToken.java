package com.curator.tokenvalidations;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class ValidateToken {
	private String secret = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
	public String extractusername(String token)
	{
		return extractAllClaims(token).getSubject();
	}
	public Date getExpirationDate(String token)
	{
		return extractAllClaims(token).getExpiration();
	}
	public String extractRole(String token) {
		System.out.println(extractAllClaims(token).get("role").toString());
		return extractAllClaims(token).get("role").toString();
	}
	public <T> Claims extractClaim(String token,Function<Claims,T> claims)
	{
		return extractAllClaims(token);
	}
	public Claims extractAllClaims(String token)
	{
		return Jwts
				.parserBuilder()
				.setSigningKey(getsignkey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	Key getsignkey() {
		byte[] keys = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keys);
	}
	public boolean isTokenExpired(String token)
	{
		return getExpirationDate(token).before(new Date());
	}
	public  boolean tokenValidation(String token)
	{
		return !isTokenExpired(token);
	}
}