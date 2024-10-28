package com.pathsnap.Backend.ImagePhoto.domain;

import com.pathsnap.Backend.Image.domain.ImageEntity;
import com.pathsnap.Backend.PhotoRecord.domain.PhotoRecordEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ImagePhoto")
@Data
public class ImagePhotoEntity {
    @Id
    private String imagePhotoId;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private ImageEntity image;

    @ManyToOne
    @JoinColumn(name = "photo_record_id")
    private PhotoRecordEntity photoRecord;

}
