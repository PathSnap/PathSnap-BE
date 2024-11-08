package com.pathsnap.Backend.ImagePhoto.Repository;

import com.pathsnap.Backend.ImagePhoto.Entity.ImagePhoto1Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagePhotoRepository extends JpaRepository<ImagePhoto1Entity,String> {
    List<ImagePhoto1Entity> findByPhotoRecord_PhotoRecordId(String photoRecordId);

}
