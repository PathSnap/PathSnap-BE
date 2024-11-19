package com.pathsnap.Backend.Record.Entity;

import com.pathsnap.Backend.Friend.Entity.Friend1Entity;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecord1Entity;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecord1Entity;
import com.pathsnap.Backend.User.Entity.User1Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "record1")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Record1Entity {
    @Id
    private String recordId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User1Entity user;

    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL)
    private List<Friend1Entity> friends;

    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL)  // 양방향 관계 설정
    private List<PhotoRecord1Entity> photoRecords;

    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL)  // 양방향 관계 설정
    private List<RouteRecord1Entity> routeRecords;

    private String recordName;
    private boolean recordIsGroup;
    private Date startDate;
    private Date endDate;

}
