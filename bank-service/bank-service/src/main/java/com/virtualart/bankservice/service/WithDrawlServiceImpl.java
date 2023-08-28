package com.virtualart.bankservice.service;
import java.time.LocalDate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.virtualart.bankservice.dto.WithdrawlDetailsDto;
import com.virtualart.bankservice.entity.AccountDetailsEntity;
import com.virtualart.bankservice.entity.TransactionEntity;
import com.virtualart.bankservice.exceptions.AccountDoesNotExistsException;
import com.virtualart.bankservice.exceptions.ErrorModel;
import com.virtualart.bankservice.exceptions.GreaterThanCurrentBalanceException;
import com.virtualart.bankservice.repository.AccountDetailsRepository;
import com.virtualart.bankservice.repository.TransactionDetailsRepository;
import com.virtualart.bankservice.response.TransactionResponse;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class WithDrawlServiceImpl implements WithDrawlService {
	@Value("${success.message}")
	private String successMessage;
	@NonNull
	private AccountDetailsRepository accountDetailsRepository;
	
	@NonNull
	private TransactionDetailsRepository transactionDetailsRepository;
	
	@NonNull
	private ModelMapper modelMapper;
	@NonNull
	private TransactionResponse<WithdrawlDetailsDto, ErrorModel> response;
	@Override
	@Transactional
	public ResponseEntity<TransactionResponse<WithdrawlDetailsDto, ErrorModel>> withdrawlAmount(
			TransactionEntity transaction) throws AccountDoesNotExistsException,GreaterThanCurrentBalanceException {
		AccountDetailsEntity accountDetails = accountDetailsRepository.findByAccountNumber(transaction.getAccountNumber());
		if(accountDetails==null) {
			throw new AccountDoesNotExistsException("Account Number does not exists");
		}
		if(transaction.getWithdrawAmount()>accountDetails.getCurrentBalance()) {
			throw new GreaterThanCurrentBalanceException("Less Balance in your Account: "+accountDetails.getCurrentBalance());
		}
		transaction.setTransactionDate(LocalDate.now());
		accountDetails.setCurrentBalance(accountDetails.getCurrentBalance()-transaction.getWithdrawAmount());
		transactionDetailsRepository.save(transaction);
		WithdrawlDetailsDto withdrawlDetails = modelMapper.map(transaction, WithdrawlDetailsDto.class);
		response.setStatus(successMessage);
		response.setData(withdrawlDetails);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
