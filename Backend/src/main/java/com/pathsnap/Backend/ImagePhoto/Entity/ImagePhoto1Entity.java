package com.pathsnap.Backend.ImagePhoto.Entity;

import com.pathsnap.Backend.Image.Entity.Image1Entity;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecord1Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "image_photo1")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImagePhoto1Entity {
    @Id
    private String imagePhotoId;

    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "imageId") // 외래 키 설정
    private Image1Entity image;

    @ManyToOne
    @JoinColumn(name = "photo_record_id")
    private PhotoRecord1Entity photoRecord;

}
