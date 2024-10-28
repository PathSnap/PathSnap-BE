package com.pathsnap.Backend.PhotoRecord.domain;

import com.pathsnap.Backend.Record.domain.RecordEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "PhotoRecord")
@Data
public class PhotoRecordEntity {
    @Id
    private String photoRecordId;

    @ManyToOne
    @JoinColumn(name = "record_id")
    private RecordEntity record;

    private int seq;
    private String photoTitle;
    private String photoContent;
    private Date photoDate;
    private double lat;
    private double lng;

}

