package com.virtualart.authentication.exceptions;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.virtualart.authentication.dto.SignUpDto;
import com.virtualart.authentication.response.Response;
import com.virtualart.authentication.response.SignUpResponse;
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
	private Response<String> response;
	@NonNull
	private ErrorModel errorModel;
	@NonNull
	private SignUpResponse<SignUpDto> signUpResponse;
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<SignUpResponse<SignUpDto>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        Map<String,String> validationerrors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(err->{
            validationerrors.put(err.getField(),err.getDefaultMessage());
        });
        signUpResponse.setStatus(failureMessage);
        signUpResponse.setError(validationerrors);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(signUpResponse);
    }
	
	@ExceptionHandler(NotValidRoleException.class)
	public ResponseEntity<Response<String>> handleNotValidRoleException(NotValidRoleException exception){
		errorModel.setCode(errorCode);
		errorModel.setMessage(exception.getMessage());
		response.setStatus(failureMessage);
		response.setErrors(errorModel);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	}
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Response<String>> handleBadCredentialsException(BadCredentialsException exception){
		errorModel.setCode(errorCode);
		errorModel.setMessage("credentials are not correct");
		response.setStatus(failureMessage);
		response.setErrors(errorModel);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<Response<String>> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception){
		errorModel.setCode(errorCode);
		errorModel.setMessage("Role Name is Required");
		response.setStatus(failureMessage);
		response.setErrors(errorModel);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
	}
}
