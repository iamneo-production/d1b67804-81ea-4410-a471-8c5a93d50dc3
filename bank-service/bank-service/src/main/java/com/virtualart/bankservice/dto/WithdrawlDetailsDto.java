package com.virtualart.bankservice.dto;
import java.time.LocalDate;
import lombok.Data;
@Data
public class WithdrawlDetailsDto {
	private Integer transactionId;
    private LocalDate transactionDate;
    private Double withdrawAmount;
}
