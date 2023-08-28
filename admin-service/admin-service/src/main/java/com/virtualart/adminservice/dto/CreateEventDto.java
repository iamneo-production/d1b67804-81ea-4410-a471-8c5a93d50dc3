package com.virtualart.adminservice.dto;
import java.time.LocalDate;
import lombok.Data;
@Data
public class CreateEventDto {
	private String eventId;
	private String exhibitionTitle; 
	private String exhibitionDescription; 
	private String exhibitionAddress;
	private LocalDate startDate;
	private LocalDate endDate;
}
