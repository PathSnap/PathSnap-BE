package com.pathsnap.Backend.Coordinate.domain;

import com.pathsnap.Backend.RouteRecord.domain.RouteRecordEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Coordinate")
@Data
public class CoordinateEntity {
    @Id
    private String coordinateId;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private RouteRecordEntity routeRecord;

    private double lat;
    private double lng;
}
