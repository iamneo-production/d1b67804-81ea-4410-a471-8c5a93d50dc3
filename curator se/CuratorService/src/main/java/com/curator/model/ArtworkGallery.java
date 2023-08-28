package com.curator.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class ArtworkGallery {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 
    private String title;
    private String description; 
    private String length;
    private String width;
    private String height;
    private String imgName;
    private String imgUrl;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private Users createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    private boolean isActive; 
    private Double imgPrice; 
    private boolean isSold;
    @OneToMany(mappedBy="galleryId")
    private List<GalleryLikeDislike> galleryLikeDislike;
    @OneToMany(mappedBy="galleryId")
    private List<GalleryComment> galleryComments;
}
