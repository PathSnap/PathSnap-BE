package com.pathsnap.Backend.TripDate.Entity;


import com.pathsnap.Backend.PackTrip.Entity.PackTrip1Entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "trip_date")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripDate1Entity {

    @Id
    private String tripDateId;

    @ManyToOne // 다대일 관계
    @JoinColumn(name = "pack_trip_id", nullable = false) // 외래키 설정
    private PackTrip1Entity packTrip;

    @Temporal(TemporalType.DATE) // 날짜 타입으로 설정
    private Date tripDate;
}
