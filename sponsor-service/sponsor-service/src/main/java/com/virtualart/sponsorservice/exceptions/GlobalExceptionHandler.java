package com.virtualart.sponsorservice.exceptions;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.virtualart.sponsorservice.response.ListResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
	@Value("${failure.message}")
	private String failureMessage;
	@Value("${error.code}")
	private String errorCode;
	@NonNull
	private ErrorModel errorModel;
	@NonNull
	private ListResponse<ErrorModel> response;
	
	@ExceptionHandler(FeignExceptions.class)
	public ResponseEntity<@NonNull ListResponse<ErrorModel>> handleFeignError(FeignExceptions exception){
		errorModel.setErrorCode(errorCode);
		errorModel.setMessage(exception.getMessage());
		response.setStatus(failureMessage);
		response.setData(errorModel);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);	
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> response(MethodArgumentNotValidException exception){
		Map<String,String> validationerrors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(err->{
            validationerrors.put(err.getField(),err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(validationerrors);
	}
}
