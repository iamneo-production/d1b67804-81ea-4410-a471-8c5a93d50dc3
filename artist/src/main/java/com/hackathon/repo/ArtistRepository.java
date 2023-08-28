package com.hackathon.artist.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackathon.artist.model.ArtworkGallery;

public interface ArtistRepository extends JpaRepository<ArtworkGallery, Integer> {


}
