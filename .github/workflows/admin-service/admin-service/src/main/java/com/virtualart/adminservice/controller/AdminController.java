package com.virtualart.adminservice.controller;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.virtualart.adminservice.dto.CreateEventDto;
import com.virtualart.adminservice.dto.InvestorDto;
import com.virtualart.adminservice.entity.ArtworkExhibitions;
import com.virtualart.adminservice.exceptions.InvalidEventIdException;
import com.virtualart.adminservice.exceptions.NoEventsException;
import com.virtualart.adminservice.exceptions.NoInvestorsException;
import com.virtualart.adminservice.response.DetailedResponse;
import com.virtualart.adminservice.response.ListResponse;
import com.virtualart.adminservice.response.Response;
import com.virtualart.adminservice.service.AdminService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
	private AdminService adminService;
	
	@PostMapping("/createEvent")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public 	ResponseEntity<Response<CreateEventDto>> createEvent(@Valid @RequestBody ArtworkExhibitions exhibition){
		return adminService.createEvent(exhibition);
	}
	@GetMapping("/getAllInvestors")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<ListResponse<List<InvestorDto>>> getAllInvestors() throws NoInvestorsException{
		return adminService.getAllInvestors();
	}
	@GetMapping("/getAllEvents")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') or hasAuthority('ROLE_SPONSOR')")
	public 	ResponseEntity<ListResponse<List<CreateEventDto>>> getAllEvents()throws NoEventsException{
		return adminService.getAllEvents();
	}
	@GetMapping("/eventDetails/{eventId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER') or hasAuthority('ROLE_SPONSOR')")
	public ResponseEntity<DetailedResponse<CreateEventDto>> getEventDetailsByEventId(@PathVariable String eventId)throws InvalidEventIdException{
		return adminService.getEventDetailsByEventId(eventId);
	}
}
