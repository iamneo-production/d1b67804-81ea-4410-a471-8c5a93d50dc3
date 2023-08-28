package com.curator.tokenvalidations;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
	
	private ValidateToken validateToken;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authhead = request.getHeader("Authorization");
		String token = null;
		String username=null;
		if(authhead!=null && authhead.startsWith("Bearer"))
		{
			token = authhead.substring(7);
			username = validateToken.extractusername(token);
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
			{
				List<SimpleGrantedAuthority> roles = Arrays.stream(validateToken.extractRole(token).split(",")).map(SimpleGrantedAuthority::new).toList();
				UserDetails user = new User(username, "secret", roles);
				if(validateToken.tokenValidation(token))
				{
					UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
					authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authtoken);
				}
			}
		}
		else {
			throw new RuntimeException(token);
		}
		filterChain.doFilter(request, response);	
	}
}
