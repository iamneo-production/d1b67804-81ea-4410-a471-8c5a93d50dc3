package com.virtualart.adminservice.service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.virtualart.adminservice.dto.InvestorDto;
import com.virtualart.adminservice.entity.Investor;
import com.virtualart.adminservice.exceptions.InvalidEventIdException;
import com.virtualart.adminservice.repository.ArtWorkExhibitionsRepository;
import com.virtualart.adminservice.repository.InvestorRepository;
import com.virtualart.adminservice.response.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class InvestorServiceImpl implements InvestorService{
	@Value("${success.message}")
	private String successMessage;
	@NonNull
	private ModelMapper modelMapper;
	@NonNull
	private Response<InvestorDto> investorResponse;
	@NonNull
	private InvestorRepository investorRepository;
	@NonNull
	private ArtWorkExhibitionsRepository artWorkExhibitionsRepository;
	@Override
	public ResponseEntity<Response<InvestorDto>> investForEvent(Investor investor) throws InvalidEventIdException {
		System.out.println(investor);
		if(artWorkExhibitionsRepository.findByEventId(investor.getEventId())==null) {
			throw new InvalidEventIdException("No such events are available under this Id: "+investor.getEventId());
		}
		investorRepository.save(investor);
		InvestorDto investorDto = modelMapper.map(investor, InvestorDto.class);
		investorResponse.setStatus(successMessage);
		investorResponse.setData(investorDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(investorResponse);
	}
}