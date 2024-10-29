package com.pathsnap.Backend.Coordinate.Entitiy;

import com.pathsnap.Backend.RouteRecord.Entity.RouteRecordEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoordinateEntity {
    @Id
    private String coordinateId;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private RouteRecordEntity routeRecord;

    private double lat;
    private double lng;
}
