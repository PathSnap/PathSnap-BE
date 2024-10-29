package com.pathsnap.Backend.ImagePhoto.Entity;

import com.pathsnap.Backend.Image.Entity.ImageEntity;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecordEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
