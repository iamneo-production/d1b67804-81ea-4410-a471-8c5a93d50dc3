package com.virtualart.sponsorservice.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.virtualart.sponsorservice.dto.CreateEventDto;
import com.virtualart.sponsorservice.dto.InvestorDto;
import com.virtualart.sponsorservice.entity.Investor;
import com.virtualart.sponsorservice.feignclients.ViewEventsFeign;
import com.virtualart.sponsorservice.response.DetailedResponse;
import com.virtualart.sponsorservice.response.ListResponse;
import com.virtualart.sponsorservice.response.Response;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class InvestorServiceImpl implements InvestorService {
	private ViewEventsFeign viewEventsFeign;
	@Override
	@CircuitBreaker(name="admin-service",fallbackMethod = "defaultGetAllEvents")
	public ResponseEntity<ListResponse<List<CreateEventDto>>> getAllEvents(){
		return viewEventsFeign.getAllEvents();
	}

	@Override
	@CircuitBreaker(name="admin-service",fallbackMethod = "defaultEventDetails")
	public ResponseEntity<DetailedResponse<CreateEventDto>> getEventDetailsByEventId(String eventId) {
		return viewEventsFeign.getEventDetailsByEventId(eventId);
	}

	@Override
	@CircuitBreaker(name="admin-service",fallbackMethod = "defaultinvestForEvent")
	public ResponseEntity<Response<InvestorDto>> investForEvent(Investor investor) {
		return viewEventsFeign.investForEvent(investor);
	}
	public ResponseEntity<ListResponse<List<CreateEventDto>>> defaultGetAllEvents(Exception e){
		CreateEventDto createEvent = new CreateEventDto();
		createEvent.setEventId("default");
		createEvent.setExhibitionAddress("default");
		List<CreateEventDto> createEventList = new ArrayList<>();
		createEventList.add(createEvent);
		ListResponse<List<CreateEventDto>> list = new ListResponse<>();
		list.setStatus("pending");
		list.setData(createEventList);
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(list);
	}
	public ResponseEntity<DetailedResponse<CreateEventDto>> defaultEventDetails(Exception e){
		CreateEventDto createEvent = new CreateEventDto();
		createEvent.setEventId("default");
		createEvent.setExhibitionAddress("default");
		DetailedResponse<CreateEventDto> response = new DetailedResponse<>();
		response.setStatus("pending");
		response.setData(createEvent);
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
	}
	public ResponseEntity<Response<InvestorDto>> defaultinvestForEvent(Exception e){
		InvestorDto investor = new InvestorDto();
		investor.setEventId("default");
		Response<InvestorDto> response = new Response<>();
		response.setStatus("pending");
		response.setData(investor);
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(response);
	}
}
