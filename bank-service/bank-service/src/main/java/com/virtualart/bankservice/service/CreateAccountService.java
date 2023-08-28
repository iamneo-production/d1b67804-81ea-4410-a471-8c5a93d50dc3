package com.virtualart.bankservice.service;
import org.springframework.http.ResponseEntity;
import com.virtualart.bankservice.dto.AccountDetailsdto;
import com.virtualart.bankservice.entity.AccountDetailsEntity;
import com.virtualart.bankservice.response.AccountDetailsResponse;
public interface CreateAccountService {
	ResponseEntity<AccountDetailsResponse<AccountDetailsdto>>createAccount(AccountDetailsEntity accountEntity);
}
