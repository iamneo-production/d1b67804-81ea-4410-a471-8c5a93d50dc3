package com.hackathon.artist.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hackathon.artist.dto.ArtistDTO;
import com.hackathon.artist.model.ArtworkGallery;
import com.hackathon.artist.model.GalleryComment;
import com.hackathon.artist.model.GalleryLikeDislike;
import com.hackathon.artist.repo.ArtistRepository;
import com.hackathon.artist.repo.CommentsRepository;
import com.hackathon.artist.repo.LikeDislikeRepository;

@Service
@Repository
public class ArtistServiceImpl implements ArtistService{
	
    @Autowired
    private ArtistRepository artistRepo;
    
    @Autowired
    private LikeDislikeRepository likeDislikeRepo;

    @Autowired
    private CommentsRepository commentsRepo;
    
    @Autowired
    private FileStorageService fileStorageService;

    
	@Override
	public ArtistDTO addPicture(ArtworkGallery gallery, MultipartFile file) {
		String fileName = fileStorageService.storeFile(file, new Date().getTime());
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        gallery.setImgName(fileName);
        gallery.setImgUrl(fileDownloadUri);
        gallery.setActive(true);
        gallery.setCreatedAt(new Date());
        ArtworkGallery artworkGallery=artistRepo.save(gallery);
		return convertEntityToDTO(artworkGallery);
	}

	@Override
	public ArtistDTO viewPicture(int id) {
		ArtworkGallery artworkGallery=artistRepo.findById(id).get();
		return convertEntityToDTO(artworkGallery);
	}

	@Override
	public String addLikeDislike(GalleryLikeDislike gallaryLikeDislike) {
		try{
			likeDislikeRepo.save(gallaryLikeDislike);
			return "success";
		}catch(Exception e) {
			throw new RuntimeException("you can not like or dislike again");
		}
		
	}

	@Override
	public String addCommentsSubComments(GalleryComment gallaryComments) {
		try{
			commentsRepo.save(gallaryComments);
			return "success";
		}catch(Exception e) {
			throw new RuntimeException("Exception "+ e);
		}
	}

	@Override
	public ArtistDTO updatePictureStatus(PictureStatusDTO statusDTO) {
		Optional<ArtworkGallery> artworkGallery=artistRepo.findById(statusDTO.getPictureId());
		if(artworkGallery.isPresent()){
			ArtworkGallery awg=artworkGallery.get();
			awg.isSold(statusDTO.isSold);
			awg.setPurchaseBy(statusDTO.getPurchaseBy());
			return convertEntityToDTO(artistRepo.save(awg));
		}else{
			throw new RuntimeException("Something went wrong");
		}
		
	}

	public ArtistDTO convertEntityToDTO(ArtworkGallery artworkGallery) {
		ArtistDTO artistDTO=new ArtistDTO();
		artistDTO.setActive(artworkGallery.isActive());
		artistDTO.setArtistName(artworkGallery.getCreatedBy().getFirstName()+" "+artworkGallery.getCreatedBy().getLastName());
		artistDTO.setCreatedAt(artworkGallery.getCreatedAt());
		artistDTO.setDescription(artworkGallery.getDescription());
		artistDTO.setDislikeCount(artworkGallery.getGalleryLikeDislike()!=null?(int)artworkGallery.getGalleryLikeDislike().stream().filter(x->x.isLikeDislike()==false).count():0);
		//artistDTO.setGalleryComments();
		artistDTO.setHeight(artworkGallery.getHeight());
		artistDTO.setId(artworkGallery.getId());
		artistDTO.setImgName(artworkGallery.getImgName());
		artistDTO.setImgPrice(artworkGallery.getImgPrice());
		artistDTO.setImgUrl(artworkGallery.getImgUrl());
		artistDTO.setLength(artworkGallery.getLength());
		artistDTO.setLikeByYou(artworkGallery.getGalleryLikeDislike()!=null?artworkGallery.getGalleryLikeDislike().stream().anyMatch(x->x.getLikeDislikeBy()==1):false);
		artistDTO.setLikeCount(artworkGallery.getGalleryLikeDislike() !=null?(int)artworkGallery.getGalleryLikeDislike().stream().filter(x->x.isLikeDislike()==true).count():0);
		artistDTO.setSold(artworkGallery.isSold());
		artistDTO.setTitle(artworkGallery.getTitle());
		artistDTO.setWidth(artworkGallery.getWidth());
		return artistDTO;
	}

}