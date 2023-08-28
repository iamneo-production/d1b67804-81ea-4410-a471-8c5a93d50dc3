package com.hackathon.artist.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PictureStatusDTO {
    private int purchaseBy;
    private int pictureId;
    private boolean isSold;
}
