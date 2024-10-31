package com.pathsnap.Backend.ImagePhoto.Repository;

import com.pathsnap.Backend.ImagePhoto.Entity.ImagePhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagePhotoRepository extends JpaRepository<ImagePhotoEntity,String> {
}
