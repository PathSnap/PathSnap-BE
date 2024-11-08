package com.pathsnap.Backend.RouteRecord.Entity;

import com.pathsnap.Backend.Coordinate.Entitiy.Coordinate1Entity;
import com.pathsnap.Backend.Record.Entity.Record1Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;



@Entity
@Table(name = "route_record")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RouteRecord1Entity {
    @Id
    private String routeId;

    @ManyToOne
    @JoinColumn(name = "record_id")
    private Record1Entity record;

    @OneToMany(mappedBy = "routeRecord", cascade = CascadeType.ALL)  // 양방향 관계 설정
    private List<Coordinate1Entity> coordinates;

    private int seq;
    @Enumerated(EnumType.STRING)
    private TransportMode transportMode;
    private Date startDate;
    private Date endDate;

    // addCoordinate 메서드 추가
    public void addCoordinate(Coordinate1Entity coordinate) {
        this.coordinates.add(coordinate);
        coordinate.setRouteRecord(this); // 양방향 관계 설정
    }
}