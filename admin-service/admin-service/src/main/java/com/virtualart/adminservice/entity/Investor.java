package com.virtualart.adminservice.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Investor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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