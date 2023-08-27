package com.hackathon.artist.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.artist.model.GalleryComment;

public interface CommentsRepository extends JpaRepository<GalleryComment, Integer>{

}
