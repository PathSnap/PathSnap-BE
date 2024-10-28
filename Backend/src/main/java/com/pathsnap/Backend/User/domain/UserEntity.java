package com.pathsnap.Backend.User.domain;

import com.pathsnap.Backend.Image.domain.ImageEntity;
import com.pathsnap.Backend.PhotoRecord.domain.PhotoRecordEntity;
import com.pathsnap.Backend.Record.domain.RecordEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "User")
@Data
public class UserEntity {
    @Id
    private String userId;

    @OneToOne
    @JoinColumn(name = "image_id")
    private ImageEntity image;

    @OneToMany(mappedBy = "user")  // 양방향 관계 설정
    private List<RecordEntity> records;

    private String phoneNumber;
    private Date birthDate;
    private String userName;
    private double lat;
    private double lng;

}
