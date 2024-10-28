package com.pathsnap.Backend.User.domain;

import com.pathsnap.Backend.Image.domain.ImageEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "User")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    @OneToOne
    @JoinColumn(name = "image_id")
    private ImageEntity image;

    private String phoneNumber;
    private Date birthDate;
    private String userName;
    private double lat;
    private double lng;

}
