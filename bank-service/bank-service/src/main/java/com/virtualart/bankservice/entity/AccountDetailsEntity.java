package com.virtualart.bankservice.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "accountNumber")
    @GenericGenerator(name="accountNumber",strategy = "com.virtualart.bankservice.entity.AccountNumberGenerator")
    private String accountNumber;

    @Pattern(regexp = "[a-zA-Z0-9' ]+",message = "Enter first name in a valid format")
    private String firstname;

    @NotBlank(message = "last name must not be empty")
    @Pattern(regexp = "[a-zA-Z0-9' ]+",message = "Enter last name in a valid format")
    private String lastname;
    
    private Double currentBalance;

    @OneToMany(targetEntity = TransactionEntity.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "accountNumber",referencedColumnName = "accountNumber")
    private List<TransactionEntity> transactions;

    @PrePersist
    public void setDefaultValue(){
        if(this.currentBalance==null){
            this.currentBalance=0.00;
        }
    }
}
