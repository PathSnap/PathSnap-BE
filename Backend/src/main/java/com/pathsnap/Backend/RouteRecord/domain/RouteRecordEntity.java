package com.pathsnap.Backend.RouteRecord.domain;

import com.pathsnap.Backend.Record.domain.RecordEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "RouteRecord")
@Data
public class RouteRecordEntity {
    @Id
    private String routeId;

    @ManyToOne
    @JoinColumn(name = "record_id")
    private RecordEntity record;

    private int seq;
    private String transportMode;
    private Date startDate;
    private Date endDate;
}
