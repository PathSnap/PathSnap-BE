package com.pathsnap.Backend.PhotoRecord.Component;

import com.pathsnap.Backend.PhotoRecord.Dto.Req.PhotoRecordEditReqDto;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecord1Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EditPhotoRecord {
    public PhotoRecord1Entity exec(PhotoRecord1Entity photoRecord, PhotoRecordEditReqDto request) {

        //PhotoRecord 수정
        photoRecord.setSeq(request.getSeq());
        photoRecord.setPhotoTitle(request.getPhotoTitle());
        photoRecord.setPhotoContent(request.getPhotoContent());
        photoRecord.setPhotoLocation(request.getPhotoLocation());
        photoRecord.setLat(request.getLat());
        photoRecord.setLng(request.getLng());
        photoRecord.setPhotoDate(request.getPhotoDate());

        return photoRecord;
    }
}
