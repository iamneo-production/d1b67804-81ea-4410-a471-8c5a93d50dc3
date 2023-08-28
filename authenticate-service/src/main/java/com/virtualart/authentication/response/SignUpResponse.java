package com.virtualart.authentication.response;
import java.util.Map;

import lombok.Data;
@Data
public class SignUpResponse<T> {
	private String status;
	private T data;
	private Map<String,String> error;
}
