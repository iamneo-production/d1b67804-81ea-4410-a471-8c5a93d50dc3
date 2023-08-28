package com.virtualart.bankservice.response;
import java.util.Map;
import lombok.Data;
@Data
public class AccountDetailsResponse<T> {
	private String status;
	private T data;
	private Map<String,String> errors;
}
