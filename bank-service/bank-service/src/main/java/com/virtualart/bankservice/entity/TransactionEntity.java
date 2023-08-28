package com.virtualart.bankservice.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;
@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;
    @NonNull
    private LocalDate transactionDate;
    @NonNull
    @Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{10}$",message = "Enter a Valid AccountNumber")
    @NotBlank(message = "account number is required")
    private String accountNumber;
    @NonNull
    private String transactionType;
    @NonNull
    private Double depositAmount;
    @NonNull
    private Double withdrawAmount;
    @PrePersist
    public void setDefaultValue(){
        if(this.depositAmount==null){
            this.depositAmount=0.00;
        }
        if(this.withdrawAmount==null){
            this.withdrawAmount=0.00;
        }
    }
}
