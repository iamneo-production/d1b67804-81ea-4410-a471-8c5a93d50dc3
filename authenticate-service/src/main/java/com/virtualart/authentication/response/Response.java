package com.virtualart.authentication.response;
import com.virtualart.authentication.exceptions.ErrorModel;
import lombok.Data;
@Data
public class Response<T> {
	private String status;
	private T data;
	private ErrorModel errors;
}
