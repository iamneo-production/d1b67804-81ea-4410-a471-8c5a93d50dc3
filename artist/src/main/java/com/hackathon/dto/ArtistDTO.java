package com.hackathon.artist.dto;

import java.util.Date;
import java.util.List;

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
public class ArtistDTO {

    private int id; 
    private String title;
    private String description; 
    private String length;
    private String width;
    private String height;
    private String imgName;
    private String imgUrl;
    private String artistName;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    private boolean isActive; 
    private Double imgPrice; 
    private boolean isSold;
    private int likeCount;
    private int dislikeCount;
    private boolean isLikeByYou;
    private List<GalleryCommentsDTO> galleryComments;
}