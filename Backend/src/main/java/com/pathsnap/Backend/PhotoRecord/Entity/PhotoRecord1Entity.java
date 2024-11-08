package com.pathsnap.Backend.PhotoRecord.Entity;

import com.pathsnap.Backend.ImagePhoto.Entity.ImagePhoto1Entity;
import com.pathsnap.Backend.Record.Entity.Record1Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "photo_record")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhotoRecord1Entity {
    @Id
    private String photoRecordId;

    @ManyToOne
    @JoinColumn(name = "record_id")
    private Record1Entity record;

    @OneToMany(mappedBy = "photoRecord", cascade = CascadeType.ALL)  // 양방향 관계 설정
    private List<ImagePhoto1Entity> imagePhotos = new ArrayList<>();

    private int seq;
    private String photoTitle;
    private String photoContent;
    private Date photoDate;
    private double lat;
    private double lng;

}

