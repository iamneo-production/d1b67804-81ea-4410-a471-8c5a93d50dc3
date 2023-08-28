package com.virtualart.bankservice.service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.virtualart.bankservice.dto.AccountDetailsdto;
import com.virtualart.bankservice.entity.AccountDetailsEntity;
import com.virtualart.bankservice.repository.AccountDetailsRepository;
import com.virtualart.bankservice.response.AccountDetailsResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CreateAccountServiceImpl implements CreateAccountService {
	@Value("${success.message}")
	private String successMessage;
	@NonNull
	private AccountDetailsRepository repository;
	@NonNull
	private AccountDetailsResponse<AccountDetailsdto> response;
	@NonNull
	private ModelMapper modelMapper;
	@Override
	public ResponseEntity<AccountDetailsResponse<AccountDetailsdto>> createAccount(AccountDetailsEntity accountEntity) {
		repository.save(accountEntity);
		AccountDetailsdto accountDetails = modelMapper.map(accountEntity, AccountDetailsdto.class);
		response.setStatus(successMessage);
		response.setData(accountDetails);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
