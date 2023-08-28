package com.virtualart.sponsorservice.feignclients;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.virtualart.sponsorservice.dto.CreateEventDto;
import com.virtualart.sponsorservice.dto.InvestorDto;
import com.virtualart.sponsorservice.entity.Investor;
import com.virtualart.sponsorservice.response.DetailedResponse;
import com.virtualart.sponsorservice.response.ListResponse;
import com.virtualart.sponsorservice.response.Response;
@FeignClient(name = "ADMIN-SERVICE",path = "/adminservice")
public interface ViewEventsFeign {
	@GetMapping("/admin/getAllEvents")
	public 	ResponseEntity<ListResponse<List<CreateEventDto>>> getAllEvents() ;
	@GetMapping("/admin/eventDetails/{eventId}")
	public ResponseEntity<DetailedResponse<CreateEventDto>> getEventDetailsByEventId(@PathVariable String eventId);
	@PostMapping("/invest/invest")
	public ResponseEntity<Response<InvestorDto>> investForEvent(@RequestBody Investor investor);
}