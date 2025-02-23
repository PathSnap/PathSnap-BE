package com.pathsnap.Backend.User.Entity;

import com.pathsnap.Backend.Friend.Entity.Friend1Entity;
import com.pathsnap.Backend.Image.Entity.Image1Entity;
import com.pathsnap.Backend.Record.Entity.Record1Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user1")
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

    @OneToMany(mappedBy = "user")
    private List<Friend1Entity> friends;

    private String phoneNumber;
    private LocalDate birthDate;
    private String email;
    private String userName;
    private String name;
    private String role;
    private String provider;
    private String provideId;
    private String address;
    private double lat;
    private double lng;
    private boolean isFirstLogin;

}
