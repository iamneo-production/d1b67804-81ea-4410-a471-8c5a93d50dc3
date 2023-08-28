package com.virtualart.authentication.configurations;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.virtualart.authentication.entity.SignUpModel;

public class UserInfoToUserService implements UserDetails {
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private List<SimpleGrantedAuthority> roles;
	public UserInfoToUserService(SignUpModel userDetails) {
		this.userName = userDetails.getEmail();
		this.password = userDetails.getPassword();
		this.roles = Arrays.stream(userDetails.getRole().split(" ")).map(SimpleGrantedAuthority::new).toList();
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
