package com.virtualart.adminservice.service;
import org.springframework.http.ResponseEntity;
import com.virtualart.adminservice.dto.InvestorDto;
import com.virtualart.adminservice.entity.Investor;
import com.virtualart.adminservice.exceptions.InvalidEventIdException;
import com.virtualart.adminservice.response.Response;

public interface InvestorService {
	ResponseEntity<Response<InvestorDto>> investForEvent(Investor investor) throws InvalidEventIdException;
}
