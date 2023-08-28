package com.virtualart.authentication.configurations;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.virtualart.authentication.entity.SignUpModel;
import com.virtualart.authentication.repository.UserRepository;

public class AuthenticateUser implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<SignUpModel> userInformation = userRepository.findByEmail(email);
		return userInformation.map(UserInfoToUserService::new).orElseThrow(()->new UsernameNotFoundException("User Does Not Exists"));
	}
}
