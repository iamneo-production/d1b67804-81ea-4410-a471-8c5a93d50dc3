package com.virtualart.sponsorservice.response;
import com.virtualart.sponsorservice.exceptions.ErrorModel;
import lombok.Data;
@Data
public class ListResponse<T> {
	private String status;
	private T data;
	private ErrorModel error;
}
