package com.pathsnap.Backend.Image.domain;

import com.pathsnap.Backend.ImagePhoto.domain.ImagePhotoEntity;
import com.pathsnap.Backend.PhotoRecord.domain.PhotoRecordEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Image")
@Data
public class ImageEntity {
    @Id
    private String imageId;
    private String url;

    @OneToMany(mappedBy = "image")  // 양방향 관계 설정
    private List<ImagePhotoEntity> imagePhotos;
}
