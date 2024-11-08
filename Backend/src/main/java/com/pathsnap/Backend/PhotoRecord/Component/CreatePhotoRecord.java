package com.pathsnap.Backend.PhotoRecord.Component;

import com.pathsnap.Backend.PhotoRecord.Dto.Req.PhotoRecordReqDto;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecordEntity;
import com.pathsnap.Backend.Record.Entity.RecordEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class CreatePhotoRecord {
    public PhotoRecordEntity exec(String photoId, RecordEntity record, PhotoRecordReqDto request) {

        return PhotoRecordEntity.builder()
                .photoRecordId(photoId)
                .record(record)
                .photoTitle(request.getPhotoTitle())
                .seq(request.getSeq())
                .photoContent(request.getPhotoContent())
                .lng(request.getLng())
                .lat(request.getLat())
                .photoDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
    }
}
