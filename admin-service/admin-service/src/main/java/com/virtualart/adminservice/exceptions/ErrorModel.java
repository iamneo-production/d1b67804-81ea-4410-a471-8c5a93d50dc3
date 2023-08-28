package com.virtualart.adminservice.exceptions;
import org.springframework.stereotype.Component;
import lombok.Data;
@Component
@Data
public class ErrorModel {
	private String errorCode;
	private String message;
}
