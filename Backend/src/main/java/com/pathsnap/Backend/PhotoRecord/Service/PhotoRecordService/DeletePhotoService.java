package com.pathsnap.Backend.PhotoRecord.Service.PhotoRecordService;

import com.pathsnap.Backend.PhotoRecord.Component.CheckPhotoRecord;
import com.pathsnap.Backend.PhotoRecord.Repository.PhotoRecordRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service("deletePhotoService")
@Builder
@AllArgsConstructor
public class DeletePhotoService {

    private final CheckPhotoRecord photoRecordCheck;
    private final PhotoRecordRepository photoRecordRepository;

    public void deletePhoto(String photoId){

        //포토기록ID있는지 확인
        photoRecordCheck.exec(photoId);
        //포토기록 삭제
        photoRecordRepository.deleteById(photoId);

    }
}
