package com.pathsnap.Backend.PackTrip.Entity;

import com.pathsnap.Backend.TripDate.Entity.TripDate1Entity;
import com.pathsnap.Backend.User.Entity.User1Entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "pack_trip1")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PackTrip1Entity {

    @Id
    private String packTripId;

    @ManyToOne // 다대일 관계
    @JoinColumn(name = "user_id", nullable = false) // 외래키 설정
    private User1Entity user;

    private String packTripName;

    @OneToMany(mappedBy = "packTrip", cascade = CascadeType.ALL) // 양방향 관계 설정
    private List<TripDate1Entity> tripDates;
}
