package com.pathsnap.Backend.Image.Entity;

import com.pathsnap.Backend.ImagePhoto.Entity.ImagePhotoEntity;
import com.pathsnap.Backend.User.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Image")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageEntity {
    @Id
    private String imageId;
    private String url;

    @OneToOne(mappedBy = "image")
    private UserEntity user;
    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL)  // 양방향 관계 설정
    private List<ImagePhotoEntity> imagePhotos;
}
