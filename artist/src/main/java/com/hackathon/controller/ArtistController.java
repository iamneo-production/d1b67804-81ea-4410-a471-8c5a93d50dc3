package com.hackathon.artist.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hackathon.artist.dto.*;
import com.hackathon.artist.model.ArtworkGallery;
import com.hackathon.artist.model.GalleryComment;
import com.hackathon.artist.model.GalleryLikeDislike;
import com.hackathon.artist.service.ArtistService;
import com.hackathon.artist.service.FileStorageService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    private ArtistService service;
    
    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("add/picture")
    public ResponseEntity<ArtistDTO> addPicture(@ModelAttribute ArtworkGallery gallery, @RequestParam("file") MultipartFile file) {
        return new ResponseEntity<ArtistDTO>(service.addPicture(gallery, file), HttpStatus.OK);
    }

    @GetMapping("view/picture/{id}")
    public ResponseEntity<ArtistDTO> viewPicture(@PathVariable int id) {
        return new ResponseEntity<ArtistDTO>(service.viewPicture(id),HttpStatus.OK);
    }


    @PostMapping("add/likedislike")
    public ResponseEntity<String> addLikeDislike(@RequestBody GalleryLikeDislike gallaryLikeDislike) {
        return new ResponseEntity<String>(service.addLikeDislike(gallaryLikeDislike),HttpStatus.OK);
    }
    
    @PostMapping("add/comment")
    public ResponseEntity<String> addCommentsSubComments(@RequestBody GalleryComment gallaryComments) {
        return new ResponseEntity<String>(service.addCommentsSubComments(gallaryComments),HttpStatus.OK);
    }

    @PostMapping("update/picture/status")
    public ResponseEntity<ArtistDTO> updatePictureStatus(@RequestBody PictureStatusDTO statusUpdate) {
        return new ResponseEntity<ArtistDTO>(service.updatePictureStatus(statusUpdate),HttpStatus.OK);
    }
    
    @GetMapping("downloadFile")
    public ResponseEntity<Resource> downloadFile(@RequestParam String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
        	
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
  


}