package com.pathsnap.Backend.Record.domain;

import com.pathsnap.Backend.User.domain.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "Record")
@Data
public class RecordEntity {
    @Id
    private String recordId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String recordName;
    private boolean recordIsGroup;
    private Date startDate;
    private Date endDate;

}
