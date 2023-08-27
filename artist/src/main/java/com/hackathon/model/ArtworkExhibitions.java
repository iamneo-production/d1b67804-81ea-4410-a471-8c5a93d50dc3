package com.hackathon.artist.model;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class ArtworkExhibitions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 
	private String exhibitionTitle; 
	private String exhibitionDescription; 
	private String exhibitionAddress; 
	private int investorId; 
	private int createdBy; 
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate; 
	private Date endDate; 
	private boolean isActive;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
        joinColumns = @JoinColumn(name = "exhibition_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "gallery_id", referencedColumnName = "id")
    )
    private Set<ArtworkGallery> artworkGallary;
	
    
}
