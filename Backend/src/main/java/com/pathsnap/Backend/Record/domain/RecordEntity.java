package com.pathsnap.Backend.Record.domain;

import com.pathsnap.Backend.PhotoRecord.domain.PhotoRecordEntity;
import com.pathsnap.Backend.RouteRecord.domain.RouteRecordEntity;
import com.pathsnap.Backend.User.domain.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Record")
@Data
public class RecordEntity {
    @Id
    private String recordId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "record")  // 양방향 관계 설정
    private List<PhotoRecordEntity> photoRecords;

    @OneToMany(mappedBy = "record")  // 양방향 관계 설정
    private List<RouteRecordEntity> routeRecords;

    private String recordName;
    private boolean recordIsGroup;
    private Date startDate;
    private Date endDate;

}
