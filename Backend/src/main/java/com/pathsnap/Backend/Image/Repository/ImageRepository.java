package com.pathsnap.Backend.Image.Repository;

import com.pathsnap.Backend.Image.Entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, String> {

}
