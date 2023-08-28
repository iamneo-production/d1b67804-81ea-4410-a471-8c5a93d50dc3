package com.virtualart.adminservice.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.virtualart.adminservice.dto.InvestorDto;
import com.virtualart.adminservice.entity.Investor;
import com.virtualart.adminservice.exceptions.InvalidEventIdException;
import com.virtualart.adminservice.response.Response;
import com.virtualart.adminservice.service.InvestorService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/invest")
@AllArgsConstructor
public class InvestorController {
	private InvestorService investorService;
	@PostMapping("/invest")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_SPONSOR')")
	public ResponseEntity<Response<InvestorDto>> investForEvent(@Valid @RequestBody Investor investor) throws InvalidEventIdException{
		return investorService.investForEvent(investor);
	}
}
