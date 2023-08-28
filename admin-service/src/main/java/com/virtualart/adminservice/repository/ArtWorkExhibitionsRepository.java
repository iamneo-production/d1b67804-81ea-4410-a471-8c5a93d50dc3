package com.virtualart.adminservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.virtualart.adminservice.entity.ArtworkExhibitions;
@Repository
public interface ArtWorkExhibitionsRepository extends JpaRepository<ArtworkExhibitions, String> {
	ArtworkExhibitions findByEventId(String eventId);
}
