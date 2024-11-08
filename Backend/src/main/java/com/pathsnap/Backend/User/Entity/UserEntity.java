package com.pathsnap.Backend.User.Entity;

import com.pathsnap.Backend.Image.Entity.ImageEntity;
import com.pathsnap.Backend.Record.Entity.RecordEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity(name = "user")
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    private String userId;

    @OneToOne
    @JoinColumn(name = "image_id")
    private ImageEntity image;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)  // 양방향 관계 설정
    private List<RecordEntity> records;

    private String phoneNumber;
    private Date birthDate;
    private String userName;
    private double lat;
    private double lng;

}
