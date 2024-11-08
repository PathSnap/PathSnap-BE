package com.pathsnap.Backend.PhotoRecord.Entity;

import com.pathsnap.Backend.ImagePhoto.Entity.ImagePhotoEntity;
import com.pathsnap.Backend.Record.Entity.RecordEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhotoRecordEntity {
    @Id
    private String photoRecordId;

    @ManyToOne
    @JoinColumn(name = "record_id")
    private RecordEntity record;

    @OneToMany(mappedBy = "photoRecord", cascade = CascadeType.ALL)  // 양방향 관계 설정
    private List<ImagePhotoEntity> imagePhotos = new ArrayList<>();

    private int seq;
    private String photoTitle;
    private String photoContent;
    private Date photoDate;
    private double lat;
    private double lng;

}

