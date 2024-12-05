package com.pathsnap.Backend.PhotoRecord.Component;

import com.pathsnap.Backend.PhotoRecord.Dto.Req.PhotoRecordCreateReqDto;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecord1Entity;
import com.pathsnap.Backend.Record.Entity.Record1Entity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class CreatePhotoRecord {
    public PhotoRecord1Entity exec(String photoId, Record1Entity record, PhotoRecordCreateReqDto request) {

        return PhotoRecord1Entity.builder()
                .photoRecordId(photoId)
                .record(record)
                .photoTitle(request.getPhotoTitle())
                .seq(request.getSeq())
                .photoLocation(request.getPhotoLocation())
                .photoContent(request.getPhotoContent())
                .lng(request.getLng())
                .lat(request.getLat())
                .photoDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
    }
}
