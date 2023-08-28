package com.virtualart.bankservice.feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "AUTHENTICATION-SERVICE",url = "/security/scope")
public interface AuthenticationFeign {
	@GetMapping("/scope/{token}/{clientId}/{clientsecret}")
	String getScope(@PathVariable("token") String token,@PathVariable("clientId")String clientId,@PathVariable("clientSecret")String clientSecret);
}
