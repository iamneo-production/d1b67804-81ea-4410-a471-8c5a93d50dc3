package com.hackathon.artist.service;

import org.springframework.web.multipart.MultipartFile;

import com.hackathon.artist.dto.*;
import com.hackathon.artist.model.ArtworkGallery;
import com.hackathon.artist.model.GalleryComment;
import com.hackathon.artist.model.GalleryLikeDislike;

public interface ArtistService {

	ArtistDTO addPicture(ArtworkGallery gallery, MultipartFile file);

	ArtistDTO viewPicture(int id);

	String addLikeDislike(GalleryLikeDislike gallaryLikeDislike);

	String addCommentsSubComments(GalleryComment gallaryComments);

	ArtistDTO updatePictureStatus(PictureStatusDTO statusDTO);

}
