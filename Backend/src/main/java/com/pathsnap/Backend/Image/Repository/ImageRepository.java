package com.pathsnap.Backend.Image.Repository;

import com.pathsnap.Backend.Image.Entity.Image1Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends JpaRepository<Image1Entity, String> {

}
