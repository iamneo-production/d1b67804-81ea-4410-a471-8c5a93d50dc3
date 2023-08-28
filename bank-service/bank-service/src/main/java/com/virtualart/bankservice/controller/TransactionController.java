package com.virtualart.bankservice.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.virtualart.bankservice.dto.DepositDetailsdto;
import com.virtualart.bankservice.dto.WithdrawlDetailsDto;
import com.virtualart.bankservice.entity.TransactionEntity;
import com.virtualart.bankservice.exceptions.AccountDoesNotExistsException;
import com.virtualart.bankservice.exceptions.ErrorModel;
import com.virtualart.bankservice.exceptions.GreaterThanCurrentBalanceException;
import com.virtualart.bankservice.response.TransactionResponse;
import com.virtualart.bankservice.service.DepositService;
import com.virtualart.bankservice.service.WithDrawlService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {
	private DepositService depositService;
	private WithDrawlService withDrawlService;
	
	@PostMapping("/deposit")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<TransactionResponse<DepositDetailsdto, ErrorModel>> depositAmount(@Valid @RequestBody TransactionEntity transaction) throws AccountDoesNotExistsException{
		return depositService.depositAmount(transaction);
	}
	@PostMapping("/withdrawl")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<TransactionResponse<WithdrawlDetailsDto, ErrorModel>> withdrawlAmount(@Valid @RequestBody TransactionEntity transaction) throws AccountDoesNotExistsException, GreaterThanCurrentBalanceException{
		return withDrawlService.withdrawlAmount(transaction);
	}
}
