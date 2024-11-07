package com.pathsnap.Backend.PhotoRecord.Service.PhotoRecordService;

import com.pathsnap.Backend.Exception.PhotoRecordNotFoundException;
import com.pathsnap.Backend.PhotoRecord.Repository.PhotoRecordRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service("deletePhotoService")
@Builder
@AllArgsConstructor
public class DeletePhotoService {

    private final PhotoRecordRepository photoRecordRepository;

    public void deletePhoto(String photoId){
        if(!photoRecordRepository.existsById(photoId)){
            throw new PhotoRecordNotFoundException(photoId);
        }
        photoRecordRepository.deleteById(photoId);

    }
}
