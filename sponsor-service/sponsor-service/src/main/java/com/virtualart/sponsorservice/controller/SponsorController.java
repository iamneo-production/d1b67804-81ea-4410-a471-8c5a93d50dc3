package com.virtualart.sponsorservice.controller;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.virtualart.sponsorservice.dto.CreateEventDto;
import com.virtualart.sponsorservice.dto.InvestorDto;
import com.virtualart.sponsorservice.entity.Investor;
import com.virtualart.sponsorservice.response.DetailedResponse;
import com.virtualart.sponsorservice.response.ListResponse;
import com.virtualart.sponsorservice.response.Response;
import com.virtualart.sponsorservice.service.InvestorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/sponsor")
@AllArgsConstructor
public class SponsorController {
	private InvestorService investorService;
	@GetMapping("/getAllEvents")
	@PreAuthorize("hasAuthority('ROLE_SPONSOR')")
	public 	ResponseEntity<ListResponse<List<CreateEventDto>>> getAllEvents(){
		return investorService.getAllEvents();
	}
	
	@GetMapping("/eventDetails/{eventId}")
	@PreAuthorize("hasAuthority('ROLE_SPONSOR')")
	public ResponseEntity<DetailedResponse<CreateEventDto>> getEventDetailsByEventId(@PathVariable String eventId){
		System.out.println(investorService.getEventDetailsByEventId(eventId));
		return investorService.getEventDetailsByEventId(eventId);
	}
	@PostMapping("/invest")
	@PreAuthorize("hasAuthority('ROLE_SPONSOR')")
	public ResponseEntity<Response<InvestorDto>> investForEvent(@Valid @RequestBody Investor investor) {
		return investorService.investForEvent(investor);
	}
}