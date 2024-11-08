package com.pathsnap.Backend.User.Entity;

import com.pathsnap.Backend.Image.Entity.Image1Entity;
import com.pathsnap.Backend.Record.Entity.Record1Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User1Entity {
    @Id
    private String userId;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image1Entity image;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)  // 양방향 관계 설정
    private List<Record1Entity> records;

    private String phoneNumber;
    private Date birthDate;
    private String userName;
    private double lat;
    private double lng;

}
