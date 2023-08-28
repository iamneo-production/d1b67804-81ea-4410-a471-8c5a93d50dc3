package com.curator.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.curator.model.ArtistDTO;
import com.curator.model.GalleryComment;
import com.curator.model.GalleryLikeDislike;

@FeignClient(name="ARTIST-SERVICE")
public interface ClientArtist {
	

    @GetMapping("view/picture/{id}")
    public ResponseEntity<ArtistDTO> viewPicture(@PathVariable int id);
    
    @PostMapping("add/likedislike")
    public ResponseEntity<String> addLikeDislike(@RequestBody GalleryLikeDislike gallaryLikeDislike);
    
    @PostMapping("add/comment")
    public ResponseEntity<String> addCommentsSubComments(@RequestBody GalleryComment gallaryComments);
}