package com.virtualart.bankservice.exceptions;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.virtualart.bankservice.dto.AccountDetailsdto;
import com.virtualart.bankservice.dto.DepositDetailsdto;
import com.virtualart.bankservice.dto.WithdrawlDetailsDto;
import com.virtualart.bankservice.response.AccountDetailsResponse;
import com.virtualart.bankservice.response.TransactionResponse;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
	@Value("${failure.message}")
	private String failMessage;
	@Value("${error.code}")
	private String errorCode;
	@NonNull
	private ErrorModel errorModel;
	@NonNull
	private AccountDetailsResponse<AccountDetailsdto> accountDetailsResponse;
	@NonNull
	private TransactionResponse<DepositDetailsdto, ErrorModel> depositDetails;
	@NonNull
	private TransactionResponse<WithdrawlDetailsDto,ErrorModel> withdrawlDetails;
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AccountDetailsResponse<AccountDetailsdto>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        Map<String,String> validationerrors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(err->{
            validationerrors.put(err.getField(),err.getDefaultMessage());
        });
        accountDetailsResponse.setErrors(validationerrors);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(accountDetailsResponse);
	}
	
	@ExceptionHandler(AccountDoesNotExistsException.class)
	public ResponseEntity<TransactionResponse<DepositDetailsdto, ErrorModel>> handleAccountDoesNotExistsException(AccountDoesNotExistsException exception){
		errorModel.setErrorCode(errorCode);
		errorModel.setMessage(exception.getMessage());
		depositDetails.setStatus(failMessage);
		depositDetails.setError(errorModel);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(depositDetails);
	}
	@ExceptionHandler(GreaterThanCurrentBalanceException.class)
	public ResponseEntity<TransactionResponse<WithdrawlDetailsDto, ErrorModel>> handleGreaterThanCurrentBalanceException(GreaterThanCurrentBalanceException exception){
		errorModel.setErrorCode(errorCode);
		errorModel.setMessage(exception.getMessage());
		withdrawlDetails.setStatus(failMessage);
		withdrawlDetails.setError(errorModel);
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(withdrawlDetails);
	}
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<ErrorModel> handleExpiredJwtException(ExpiredJwtException exception){
		errorModel.setErrorCode(errorCode);
		errorModel.setMessage("Jwt Token is Expired");
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorModel);
	}
}
