package com.virtualart.adminservice.service;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.virtualart.adminservice.dto.CreateEventDto;
import com.virtualart.adminservice.dto.InvestorDto;
import com.virtualart.adminservice.entity.ArtworkExhibitions;
import com.virtualart.adminservice.exceptions.InvalidEventIdException;
import com.virtualart.adminservice.exceptions.NoEventsException;
import com.virtualart.adminservice.exceptions.NoInvestorsException;
import com.virtualart.adminservice.response.DetailedResponse;
import com.virtualart.adminservice.response.ListResponse;
import com.virtualart.adminservice.response.Response;
public interface AdminService {
	ResponseEntity<Response<CreateEventDto>> createEvent(ArtworkExhibitions exhibition);
	ResponseEntity<ListResponse<List<InvestorDto>>> getAllInvestors() throws NoInvestorsException;//only admin has the access
	ResponseEntity<ListResponse<List<CreateEventDto>>> getAllEvents()throws NoEventsException ; // admin,user,investor has the access
	ResponseEntity<DetailedResponse<CreateEventDto>> getEventDetailsByEventId(String eventId)throws InvalidEventIdException; // admin,user,investor has the access
}
