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

    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "imageId") // 외래 키 설정
    private ImageEntity image;

    @ManyToOne
    @JoinColumn(name = "photo_record_id")
    private PhotoRecordEntity photoRecord;

}
