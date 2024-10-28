package com.pathsnap.Backend.RouteRecord.domain;

import com.pathsnap.Backend.Coordinate.domain.CoordinateEntity;
import com.pathsnap.Backend.PhotoRecord.domain.PhotoRecordEntity;
import com.pathsnap.Backend.Record.domain.RecordEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "RouteRecord")
@Data
public class RouteRecordEntity {
    @Id
    private String routeId;

    @ManyToOne
    @JoinColumn(name = "record_id")
    private RecordEntity record;

    @OneToMany(mappedBy = "routeRecord")  // 양방향 관계 설정
    private List<CoordinateEntity> coordinates;

    private int seq;
    private String transportMode;
    private Date startDate;
    private Date endDate;
}
