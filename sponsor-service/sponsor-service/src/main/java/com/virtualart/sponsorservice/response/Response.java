package com.virtualart.sponsorservice.response;
import java.util.Map;
import lombok.Data;
@Data
public class Response<T> {
	private String status;
	private T data;
	private Map<String,String> errors;
}
