package com.virtualart.bankservice.service;
import org.springframework.http.ResponseEntity;
import com.virtualart.bankservice.dto.DepositDetailsdto;
import com.virtualart.bankservice.entity.TransactionEntity;
import com.virtualart.bankservice.exceptions.AccountDoesNotExistsException;
import com.virtualart.bankservice.exceptions.ErrorModel;
import com.virtualart.bankservice.response.TransactionResponse;
public interface DepositService {
	ResponseEntity<TransactionResponse<DepositDetailsdto, ErrorModel>> depositAmount(
			TransactionEntity transaction) throws AccountDoesNotExistsException;
}
