package com.virtualart.sponsorservice.feignclients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
@Component
public class PassTokens implements RequestInterceptor{
	private final HttpServletRequest request;
	@Autowired
	public PassTokens(HttpServletRequest request) {
		this.request=request;
	}
	@Override
	public void apply(RequestTemplate template) {
		template.header("Authorization",request.getHeader("Authorization"));
		System.out.println(template);
	}
}