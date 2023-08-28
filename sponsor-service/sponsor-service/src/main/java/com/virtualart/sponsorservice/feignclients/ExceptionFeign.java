package com.virtualart.sponsorservice.feignclients;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.virtualart.sponsorservice.exceptions.FeignExceptions;
import feign.Response;
import feign.codec.ErrorDecoder;
@Component
public class ExceptionFeign implements ErrorDecoder {
	@Override
	public Exception decode(String methodKey, Response response) {
		Map<Integer,Exception> errorHandling = new HashMap<>();
		errorHandling.put(403, new FeignExceptions("Exception in the Admin Side"));
		System.out.println(response.status());
		return errorHandling.get(response.status());
	}
}