package com.virtualart.bankservice.service;
import org.springframework.http.ResponseEntity;

import com.virtualart.bankservice.dto.WithdrawlDetailsDto;
import com.virtualart.bankservice.entity.TransactionEntity;
import com.virtualart.bankservice.exceptions.AccountDoesNotExistsException;
import com.virtualart.bankservice.exceptions.ErrorModel;
import com.virtualart.bankservice.exceptions.GreaterThanCurrentBalanceException;
import com.virtualart.bankservice.response.TransactionResponse;

public interface WithDrawlService {
	ResponseEntity<TransactionResponse<WithdrawlDetailsDto, ErrorModel>> withdrawlAmount(
			TransactionEntity transaction) throws AccountDoesNotExistsException,GreaterThanCurrentBalanceException;
}
