package com.virtualart.adminservice.response;
import com.virtualart.adminservice.exceptions.ErrorModel;
import lombok.Data;
@Data
public class DetailedResponse<T> {
	private String status;
	private T data;
	private ErrorModel errors;
}
