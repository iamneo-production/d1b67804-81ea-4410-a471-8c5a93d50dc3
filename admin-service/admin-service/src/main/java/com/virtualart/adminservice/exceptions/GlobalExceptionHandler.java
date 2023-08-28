package com.virtualart.adminservice.exceptions;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.virtualart.adminservice.dto.CreateEventDto;
import com.virtualart.adminservice.dto.InvestorDto;
import com.virtualart.adminservice.response.DetailedResponse;
import com.virtualart.adminservice.response.ListResponse;
import com.virtualart.adminservice.response.Response;

import io.jsonwebtoken.ExpiredJwtException;
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
	private Response<InvestorDto> validationResponse;
	@NonNull
	ListResponse<List<InvestorDto>> getAllInvestorsResponse;
	@NonNull
	ListResponse<List<CreateEventDto>> getAllEventsResponse;
	@NonNull
	DetailedResponse<CreateEventDto> getEventDetailsResponse;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<InvestorDto>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        Map<String,String> validationerrors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(err->{
            validationerrors.put(err.getField(),err.getDefaultMessage());
        });
        validationResponse.setErrors(validationerrors);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(validationResponse);
	}
	@ExceptionHandler(NoInvestorsException.class)
	public ResponseEntity<ListResponse<List<InvestorDto>>> handleNoInvestorsException(NoInvestorsException exception){
		errorModel.setErrorCode(errorCode);
		errorModel.setMessage(exception.getMessage());
		getAllInvestorsResponse.setStatus(failureMessage);
		getAllInvestorsResponse.setError(errorModel);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(getAllInvestorsResponse);
	}
	@ExceptionHandler(NoEventsException.class)
	public ResponseEntity<ListResponse<List<CreateEventDto>>> handleNoEventsException(NoEventsException exception){
		errorModel.setErrorCode(errorCode);
		errorModel.setMessage(exception.getMessage());
		getAllEventsResponse.setStatus(failureMessage);
		getAllEventsResponse.setError(errorModel);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getAllEventsResponse);
	}
	@ExceptionHandler(InvalidEventIdException.class)
	public ResponseEntity<DetailedResponse<CreateEventDto>>handleInvalidEventIdException(InvalidEventIdException exception){
		errorModel.setErrorCode(errorCode);
		errorModel.setMessage(exception.getMessage());
		getEventDetailsResponse.setErrors(errorModel);
		getEventDetailsResponse.setStatus(failureMessage);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getEventDetailsResponse);
	}
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<ErrorModel> handleExpiredJwtException(ExpiredJwtException exception){
		errorModel.setErrorCode(errorCode);
		errorModel.setMessage("Jwt Token is Expired");
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorModel);
	}
}