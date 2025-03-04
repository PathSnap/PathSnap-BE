package com.pathsnap.Backend.Coordinate.Entitiy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecord1Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "coordinate1")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Coordinate1Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 증가
    private Long coordinateId;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private RouteRecord1Entity routeRecord;

    private double lat;
    private double lng;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timeStamp; // 좌표 기록 시간을 저장
}
