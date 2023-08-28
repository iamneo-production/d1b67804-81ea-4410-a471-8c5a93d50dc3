package com.virtualart.sponsorservice.service;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.virtualart.sponsorservice.dto.CreateEventDto;
import com.virtualart.sponsorservice.dto.InvestorDto;
import com.virtualart.sponsorservice.entity.Investor;
import com.virtualart.sponsorservice.response.DetailedResponse;
import com.virtualart.sponsorservice.response.ListResponse;
import com.virtualart.sponsorservice.response.Response;
public interface InvestorService {
	ResponseEntity<ListResponse<List<CreateEventDto>>> getAllEvents();
	ResponseEntity<DetailedResponse<CreateEventDto>> getEventDetailsByEventId(String eventId);
	ResponseEntity<Response<InvestorDto>> investForEvent(Investor investor);
}
