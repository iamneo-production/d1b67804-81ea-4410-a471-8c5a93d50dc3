package com.curator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.curator.client.ClientArtist;
import com.curator.model.ArtistDTO;
import com.curator.model.GalleryComment;
import com.curator.model.GalleryLikeDislike;
import com.curator.service.CuratorService;

@RestController
public class CuratorController {
	
	@Autowired
	private CuratorService curatorService;
	private ClientArtist clientArtist;
	
	@GetMapping("/findAll")
	public ResponseEntity<ArtistDTO> getArtists(@PathVariable int id) throws Exception {
		return curatorService.viewPicture(id);
	}
    @PostMapping("add/likedislike")
    public ResponseEntity<String> addLikeDislike(@RequestBody GalleryLikeDislike gallaryLikeDislike) {
        return curatorService.addLikeDislike(gallaryLikeDislike);
    }
    @PostMapping("add/comment")
    public ResponseEntity<String> addCommentsSubComments(@RequestBody GalleryComment gallaryComments) {
    	return curatorService.addCommentsSubComments(gallaryComments);
    }
	

}
