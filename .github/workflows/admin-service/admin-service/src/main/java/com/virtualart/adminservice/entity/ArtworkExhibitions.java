package com.virtualart.adminservice.entity;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArtworkExhibitions {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "eventId")
    @GenericGenerator(name="eventId",strategy = "com.virtualart.adminservice.entity.EventNumberGenerator")
    private String eventId;
	@NotBlank(message = "exhibition Title is Required")
	private String exhibitionTitle; 
	@NotBlank(message = "exhibition Description is Required")
	private String exhibitionDescription; 
	@NotBlank(message = "exhibition Address is Required")
	private String exhibitionAddress;
	@NotNull(message = "start Date is required")
	private LocalDate startDate;
	@NotNull(message = "End Date is required")
	private LocalDate endDate;
    @OneToMany(targetEntity = Investor.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "eventId",referencedColumnName = "eventId")
    private List<Investor> investors;
}
