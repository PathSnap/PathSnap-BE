package com.pathsnap.Backend.PhotoRecord.Service;

import com.pathsnap.Backend.PhotoRecord.Component.CheckPhotoRecord;
import com.pathsnap.Backend.PhotoRecord.Repository.PhotoRecordRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class DeletePhotoService {

    private final CheckPhotoRecord photoRecordCheck;
    private final PhotoRecordRepository photoRecordRepository;

    public void deletePhoto(String photoId){

        //photoId있는지 확인
        photoRecordCheck.exec(photoId);

        //photoRecord 삭제
        photoRecordRepository.deleteById(photoId);

    }
}
