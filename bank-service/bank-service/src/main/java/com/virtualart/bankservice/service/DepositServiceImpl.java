package com.virtualart.bankservice.service;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.virtualart.bankservice.dto.DepositDetailsdto;
import com.virtualart.bankservice.entity.AccountDetailsEntity;
import com.virtualart.bankservice.entity.TransactionEntity;
import com.virtualart.bankservice.exceptions.AccountDoesNotExistsException;
import com.virtualart.bankservice.exceptions.ErrorModel;
import com.virtualart.bankservice.repository.AccountDetailsRepository;
import com.virtualart.bankservice.repository.TransactionDetailsRepository;
import com.virtualart.bankservice.response.TransactionResponse;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {
	@Value("${success.message}")
	private String successMessage;
	@NonNull
	private AccountDetailsRepository accountDetailsRepository;
	
	@NonNull
	private TransactionDetailsRepository transactionDetailsRepository;
	
	@NonNull
	private ModelMapper modelMapper;
	@NonNull
	private TransactionResponse<DepositDetailsdto, ErrorModel> response;
	@Override
	@Transactional
	public ResponseEntity<TransactionResponse<DepositDetailsdto, ErrorModel>> depositAmount(
			TransactionEntity transaction) throws AccountDoesNotExistsException {
		AccountDetailsEntity accountDetails = accountDetailsRepository.findByAccountNumber(transaction.getAccountNumber());
		if(accountDetails==null) {
			throw new AccountDoesNotExistsException("Account Number does not exists");
		}
		transaction.setTransactionDate(LocalDate.now());
		accountDetails.setCurrentBalance(transaction.getDepositAmount()+accountDetails.getCurrentBalance());
		transactionDetailsRepository.save(transaction);
		DepositDetailsdto depositDetails = modelMapper.map(transaction, DepositDetailsdto.class);
		response.setStatus(successMessage);
		response.setData(depositDetails);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
