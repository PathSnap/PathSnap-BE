package com.pathsnap.Backend.PhotoRecord.Service.PhotoRecordService;

import com.pathsnap.Backend.Exception.PhotoRecordNotFoundException;
import com.pathsnap.Backend.PhotoRecord.Repository.PhotoRecordRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("deletePhotoService")
@Builder
public class DeletePhotoService {

    @Autowired
    PhotoRecordRepository photoRecordRepository;

    public void deletePhoto(String photoId){
        if(!photoRecordRepository.existsById(photoId)){
            throw new PhotoRecordNotFoundException(photoId);
        }
        photoRecordRepository.deleteById(photoId);

    }
}
