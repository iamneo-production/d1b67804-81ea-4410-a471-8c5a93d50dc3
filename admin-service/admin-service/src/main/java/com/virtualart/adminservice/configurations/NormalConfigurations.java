package com.virtualart.adminservice.configurations;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import com.virtualart.adminservice.dto.CreateEventDto;
import com.virtualart.adminservice.dto.InvestorDto;
import com.virtualart.adminservice.response.DetailedResponse;
import com.virtualart.adminservice.response.ListResponse;
import com.virtualart.adminservice.response.Response;
@Configuration
public class NormalConfigurations {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	@Scope("prototype")
	public Response<CreateEventDto> createResponse(){
		return new Response<>();
	}
	
	@Bean
	@Scope("prototype")
	public ListResponse<List<InvestorDto>> listResponse(){
		return new ListResponse<>();
	}
	@Bean
	@Scope("prototype")
	public ListResponse<List<CreateEventDto>> allEventsResponse(){
		return new ListResponse<>();
	}
	@Bean
	@Scope("prototype")
	public DetailedResponse<CreateEventDto> detailedEventResponse(){
		return new DetailedResponse<>();
	}
	@Bean
	@Scope("prototype")
	public Response<InvestorDto> investorResponse(){
		return new Response<>();
	}
}
