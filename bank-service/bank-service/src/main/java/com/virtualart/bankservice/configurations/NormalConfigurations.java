package com.virtualart.bankservice.configurations;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import com.virtualart.bankservice.dto.AccountDetailsdto;
import com.virtualart.bankservice.dto.DepositDetailsdto;
import com.virtualart.bankservice.dto.WithdrawlDetailsDto;
import com.virtualart.bankservice.exceptions.ErrorModel;
import com.virtualart.bankservice.response.AccountDetailsResponse;
import com.virtualart.bankservice.response.TransactionResponse;
@Configuration
public class NormalConfigurations {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Bean
	@Scope("prototype")
	public AccountDetailsResponse<AccountDetailsdto> accountDetailsResponse(){
		return new AccountDetailsResponse<>();
	}
	@Bean
	@Scope("prototype")
	public TransactionResponse<DepositDetailsdto, ErrorModel> depositTransactionResponse(){
		return new TransactionResponse<>();
	}
	@Bean
	@Scope("prototype")
	public TransactionResponse<WithdrawlDetailsDto, ErrorModel> withdrawlTransactionResponse(){
		return new TransactionResponse<>();
	}
}
