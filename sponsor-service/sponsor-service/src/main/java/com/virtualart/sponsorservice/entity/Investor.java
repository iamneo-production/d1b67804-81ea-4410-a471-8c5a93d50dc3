package com.virtualart.sponsorservice.entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Investor {
	@NotBlank(message = "name is required")
    private String name;
	@NotBlank(message = "address is required")
    private String address;
	@NotBlank(message = "EventId is required")
	@Size(min=10,max=10,message = "Enter a valid Event Id")
	private String eventId;
	@NotBlank(message = "PAN number is required")
	@Size(min=10,max=10,message = "Enter a Valid PAN Number")
    private String pan;
	@NotBlank(message = "GST number is required")
	@Size(min=15,max=15,message = "Enter a Valid GST Number")
    private String gst;
}