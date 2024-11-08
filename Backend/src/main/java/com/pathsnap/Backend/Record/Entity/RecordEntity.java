package com.pathsnap.Backend.Record.Entity;

import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecord1Entity;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecordEntity;
import com.pathsnap.Backend.User.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "record")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordEntity {
    @Id
    private String recordId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL)  // 양방향 관계 설정
    private List<PhotoRecord1Entity> photoRecords;

    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL)  // 양방향 관계 설정
    private List<RouteRecordEntity> routeRecords;

    private String recordName;
    private boolean recordIsGroup;
    private Date startDate;
    private Date endDate;

}
