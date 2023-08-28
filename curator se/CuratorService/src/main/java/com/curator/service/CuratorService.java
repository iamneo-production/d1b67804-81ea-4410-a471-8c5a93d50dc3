package com.curator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.curator.client.ClientArtist;
import com.curator.model.ArtistDTO;
import com.curator.model.GalleryComment;
import com.curator.model.GalleryLikeDislike;

@Service
public class CuratorService {

	@Autowired
	private ClientArtist clientArtist;

	public  ResponseEntity<ArtistDTO> viewPicture(int id) throws Exception {
		try {
		ResponseEntity<ArtistDTO> artistDTO= clientArtist.viewPicture(id);	
		return artistDTO;
		}
		catch(Exception ex)
		{
		throw new Exception(ex); 
		}
	}

	public ResponseEntity<String> addLikeDislike(GalleryLikeDislike gallaryLikeDislike) {

		return clientArtist.addLikeDislike(gallaryLikeDislike);
	}

	public ResponseEntity<String> addCommentsSubComments(GalleryComment gallaryComments) {
		return clientArtist.addCommentsSubComments(gallaryComments);
	}

}
