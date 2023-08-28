package com.virtualart.adminservice.service;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.virtualart.adminservice.dto.CreateEventDto;
import com.virtualart.adminservice.dto.InvestorDto;
import com.virtualart.adminservice.entity.ArtworkExhibitions;
import com.virtualart.adminservice.entity.Investor;
import com.virtualart.adminservice.exceptions.InvalidEventIdException;
import com.virtualart.adminservice.exceptions.NoEventsException;
import com.virtualart.adminservice.exceptions.NoInvestorsException;
import com.virtualart.adminservice.repository.ArtWorkExhibitionsRepository;
import com.virtualart.adminservice.repository.InvestorRepository;
import com.virtualart.adminservice.response.DetailedResponse;
import com.virtualart.adminservice.response.ListResponse;
import com.virtualart.adminservice.response.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
	@Value("${success.message}")
	private String successMessage;
	
	@NonNull
	private ModelMapper modelMapper;
	
	@NonNull
	private Response<CreateEventDto> createEventResponse;
	@NonNull
	private ListResponse<List<InvestorDto>> allinvestorsResponse;
	@NonNull
	private ListResponse<List<CreateEventDto>> allEventsResponse;
	@NonNull
	private DetailedResponse<CreateEventDto> eventDetailsResponse;
	@NonNull
	private ArtWorkExhibitionsRepository artWorkExhibitionsRepository;
	@NonNull
	private InvestorRepository investorRepository;
	
	@Override
	public ResponseEntity<Response<CreateEventDto>> createEvent(ArtworkExhibitions exhibition) {
		artWorkExhibitionsRepository.save(exhibition);
		CreateEventDto eventDetailsDto = modelMapper.map(exhibition, CreateEventDto.class);
		createEventResponse.setStatus(successMessage);
		createEventResponse.setData(eventDetailsDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createEventResponse);
	}

	@Override
	public ResponseEntity<ListResponse<List<InvestorDto>>> getAllInvestors() throws NoInvestorsException {
		List<Investor> investors = investorRepository.findAll();
		if(investors.isEmpty()) {
			throw new NoInvestorsException("No investors available");
		}
		List<InvestorDto> investorResponse = investors.stream().map(investor->modelMapper.map(investor, InvestorDto.class)).toList();
		allinvestorsResponse.setStatus(successMessage);
		allinvestorsResponse.setData(investorResponse);
		return ResponseEntity.status(HttpStatus.OK).body(allinvestorsResponse);
	}
	

	@Override
	public ResponseEntity<ListResponse<List<CreateEventDto>>> getAllEvents() throws NoEventsException {
		List<ArtworkExhibitions> events = artWorkExhibitionsRepository.findAll();
		if(events.isEmpty()) {
			throw new NoEventsException("No Events available");
		}
		List<CreateEventDto> eventResponse = events.stream().map(event->modelMapper.map(event, CreateEventDto.class)).toList();
		allEventsResponse.setStatus(successMessage);
		allEventsResponse.setData(eventResponse);
		return ResponseEntity.status(HttpStatus.OK).body(allEventsResponse);
	}

	@Override
	public ResponseEntity<DetailedResponse<CreateEventDto>> getEventDetailsByEventId(String eventId) throws InvalidEventIdException {
		ArtworkExhibitions event = artWorkExhibitionsRepository.findByEventId(eventId);
		if(event==null) {
			throw new InvalidEventIdException("No such events are available under this Id: "+eventId);
		}
		CreateEventDto eventdto = modelMapper.map(event,CreateEventDto.class);
		eventDetailsResponse.setStatus(successMessage);
		eventDetailsResponse.setData(eventdto);
		return ResponseEntity.status(HttpStatus.OK).body(eventDetailsResponse);
	}
}