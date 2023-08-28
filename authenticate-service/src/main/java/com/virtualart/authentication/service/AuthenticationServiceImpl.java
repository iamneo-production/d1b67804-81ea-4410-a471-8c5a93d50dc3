package com.virtualart.authentication.service;
import java.util.List;
import javax.security.auth.login.AccountNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.virtualart.authentication.dto.SignUpDto;
import com.virtualart.authentication.entity.SignInModel;
import com.virtualart.authentication.entity.SignUpModel;
import com.virtualart.authentication.exceptions.NotValidRoleException;
import com.virtualart.authentication.repository.UserRepository;
import com.virtualart.authentication.response.SignUpResponse;
import com.virtualart.authentication.tokengeneration.GenerateJwtToken;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
	@Value("${success.message}")
	private String successMessage;	
	@Value("${role.list}")
	private List<String> roles;
	@NonNull
	private UserRepository userRepository;
	@NonNull
	private ModelMapper modelMapper;
	@NonNull
	private PasswordEncoder passwordEncoder;
	@NonNull
	private SignUpResponse<SignUpDto> response;
	@NonNull
	private GenerateJwtToken generateToken;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Override
	public ResponseEntity<SignUpResponse<SignUpDto>> register(String role, SignUpModel signUpModel) throws NotValidRoleException {
		System.out.println(roles);
		if(!roles.contains(role)) {
			throw new NotValidRoleException("Enter a Valid Role: "+role);
		}
		signUpModel.setPassword(passwordEncoder.encode(signUpModel.getPassword()));
		signUpModel.setRole(role);
		System.out.println(signUpModel);
		userRepository.save(signUpModel);
		SignUpDto dto = modelMapper.map(signUpModel, SignUpDto.class);
		response.setStatus(successMessage);
		response.setData(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@Override
	public ResponseEntity<String> authenticate(SignInModel loginInModel) throws AccountNotFoundException {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginInModel.getUserName(), loginInModel.getPassword()));
		if(!authentication.isAuthenticated()) {
			throw new AccountNotFoundException("Invalid Credentials");
		}
		return ResponseEntity.status(HttpStatus.OK).body(generateToken.generateToken(loginInModel.getUserName()));
	}
}