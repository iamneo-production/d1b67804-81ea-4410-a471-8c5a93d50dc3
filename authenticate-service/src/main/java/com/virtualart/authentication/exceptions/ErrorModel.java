package com.virtualart.authentication.exceptions;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ErrorModel {
	private String code;
	private String message;
}
