package com.virtualart.bankservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtualart.bankservice.dto.AccountDetailsdto;
import com.virtualart.bankservice.entity.AccountDetailsEntity;
import com.virtualart.bankservice.response.AccountDetailsResponse;
import com.virtualart.bankservice.service.CreateAccountService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountCreateController {
	private CreateAccountService createAccountService;
	@PostMapping("/accountcreate")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<AccountDetailsResponse<AccountDetailsdto>> createAccount(@Valid @RequestBody AccountDetailsEntity accountDetailsEntity){
		return createAccountService.createAccount(accountDetailsEntity);
	}
}
