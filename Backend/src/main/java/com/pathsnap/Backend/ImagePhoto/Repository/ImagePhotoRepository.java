package com.pathsnap.Backend.ImagePhoto.Repository;

import com.pathsnap.Backend.ImagePhoto.Entity.ImagePhotoEntity;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ImagePhotoRepository extends JpaRepository<ImagePhotoEntity,String> {
    List<ImagePhotoEntity> findByPhotoRecord_PhotoRecordId(String photoRecordId);

}
