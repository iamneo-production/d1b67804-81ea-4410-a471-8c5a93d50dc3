package com.virtualart.authentication.configurations;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import com.virtualart.authentication.dto.SignUpDto;
import com.virtualart.authentication.response.Response;
import com.virtualart.authentication.response.SignUpResponse;
@Configuration
public class ApplicationConfigurations {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Bean
	@Scope("prototype")
	public SignUpResponse<SignUpDto> signupResponse(){
		return new SignUpResponse<>();
	}
	@Bean
	@Scope("prototype")
	public Response<String> response(){
		return new Response<>();
	}
}
