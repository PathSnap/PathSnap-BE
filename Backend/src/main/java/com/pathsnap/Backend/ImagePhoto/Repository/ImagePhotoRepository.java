package com.pathsnap.Backend.ImagePhoto.Repository;

import com.pathsnap.Backend.ImagePhoto.Entity.ImagePhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagePhotoRepository extends JpaRepository<ImagePhotoEntity,String> {
    List<ImagePhotoEntity> findByPhotoRecord_PhotoRecordId(String photoRecordId);

}
