package com.pathsnap.Backend.PackTrip.Entity;

import com.pathsnap.Backend.TripDate.Entity.TripDateEntity;
import com.pathsnap.Backend.User.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PackTripEntity {

    @Id
    private String packTripId;

    @ManyToOne // 다대일 관계
    @JoinColumn(name = "user_id", nullable = false) // 외래키 설정
    private UserEntity user;

    private String packTripName;

    @OneToMany(mappedBy = "packTrip", cascade = CascadeType.ALL) // 양방향 관계 설정
    private List<TripDateEntity> tripDates;
}
