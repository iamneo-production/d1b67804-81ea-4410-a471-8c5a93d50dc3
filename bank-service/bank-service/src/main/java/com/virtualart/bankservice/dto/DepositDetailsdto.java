package com.virtualart.bankservice.dto;
import java.time.LocalDate;
import lombok.Data;
@Data
public class DepositDetailsdto {
	private Integer transactionId;
    private LocalDate transactionDate;
    private Double depositAmount;
}
