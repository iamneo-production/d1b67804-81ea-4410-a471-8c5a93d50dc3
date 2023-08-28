package com.virtualart.authentication.controller;
import javax.security.auth.login.AccountNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.virtualart.authentication.dto.SignUpDto;
import com.virtualart.authentication.entity.SignInModel;
import com.virtualart.authentication.entity.SignUpModel;
import com.virtualart.authentication.exceptions.NotValidRoleException;
import com.virtualart.authentication.response.SignUpResponse;
import com.virtualart.authentication.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {
	private AuthenticationService authenticationService;
	@PostMapping("/register")
	public ResponseEntity<SignUpResponse<SignUpDto>> register(@RequestParam("role")String role,@Valid @RequestBody SignUpModel signupModel) throws NotValidRoleException{
		return authenticationService.register(role, signupModel);
	}
	@PostMapping("/authenticate")
	public ResponseEntity<String> authenticate(@RequestBody SignInModel loginInModel) throws AccountNotFoundException {
		return authenticationService.authenticate(loginInModel);
	}
}
