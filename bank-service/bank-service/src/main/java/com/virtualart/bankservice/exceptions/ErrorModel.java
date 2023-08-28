package com.virtualart.bankservice.exceptions;
import org.springframework.stereotype.Component;
import lombok.Data;
@Data
@Component
public class ErrorModel {
	private String errorCode;
	private String message;
}
