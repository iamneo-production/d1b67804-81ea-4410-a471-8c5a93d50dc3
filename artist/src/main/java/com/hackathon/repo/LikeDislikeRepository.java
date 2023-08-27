package com.hackathon.artist.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.artist.model.GalleryLikeDislike;

public interface LikeDislikeRepository extends JpaRepository<GalleryLikeDislike, Integer> {

}
