package com.pathsnap.Backend.Image.Entity;

import com.pathsnap.Backend.Image.Repository.ImageRepository;
import com.pathsnap.Backend.ImagePhoto.Entity.ImagePhotoEntity;
import com.pathsnap.Backend.User.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "image")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageEntity {
    @Id
    private String imageId;
    private String url;
    private String fileKey;

    @OneToOne(mappedBy = "image")
    private UserEntity user;
    @OneToOne(mappedBy = "image")
    private ImagePhotoEntity imagePhotos;
}
