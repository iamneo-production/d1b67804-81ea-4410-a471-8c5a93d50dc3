package com.virtualart.authentication.service;
import javax.security.auth.login.AccountNotFoundException;

import org.springframework.http.ResponseEntity;
import com.virtualart.authentication.dto.SignUpDto;
import com.virtualart.authentication.entity.SignInModel;
import com.virtualart.authentication.entity.SignUpModel;
import com.virtualart.authentication.exceptions.NotValidRoleException;
import com.virtualart.authentication.response.SignUpResponse;
public interface AuthenticationService {
	ResponseEntity<SignUpResponse<SignUpDto>> register(String role,SignUpModel signUpModel)throws NotValidRoleException;
	ResponseEntity<String> authenticate(SignInModel loginInModel)throws AccountNotFoundException;
}
