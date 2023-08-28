package com.virtualart.bankservice.response;
import lombok.Data;
@Data
public class TransactionResponse<T,E> {
	private String status;
	private T data;
	private E error;
}
