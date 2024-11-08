package com.pathsnap.Backend.PhotoRecord.Component;

import com.pathsnap.Backend.PhotoRecord.Dto.Req.PhotoRecordReqDto;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecord1Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class EditPhotoRecord {
    public PhotoRecord1Entity exec(PhotoRecord1Entity photoRecord, PhotoRecordReqDto request) {

        //PhotoRecord 수정
        photoRecord.setSeq(request.getSeq());
        photoRecord.setPhotoTitle(request.getPhotoTitle());
        photoRecord.setPhotoContent(request.getPhotoContent());
        photoRecord.setLat(request.getLat());
        photoRecord.setLng(request.getLng());
        photoRecord.setPhotoDate(Date.from(LocalDate.parse(request.getPhotoDate()).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        return photoRecord;
    }
}
