package com.pathsnap.Backend.PhotoRecord.domain;

import com.pathsnap.Backend.Image.domain.ImageEntity;
import com.pathsnap.Backend.ImagePhoto.domain.ImagePhotoEntity;
import com.pathsnap.Backend.Record.domain.RecordEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PhotoRecord")
@Data
public class PhotoRecordEntity {
    @Id
    private String photoRecordId;

    @ManyToOne
    @JoinColumn(name = "record_id")
    private RecordEntity record;

    @OneToMany(mappedBy = "photoRecord")  // 양방향 관계 설정
    private List<ImagePhotoEntity> imagePhotos;

    private int seq;
    private String photoTitle;
    private String photoContent;
    private Date photoDate;
    private double lat;
    private double lng;

}

