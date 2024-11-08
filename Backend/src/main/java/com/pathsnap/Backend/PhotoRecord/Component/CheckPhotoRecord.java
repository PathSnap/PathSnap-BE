package com.pathsnap.Backend.PhotoRecord.Component;

import com.pathsnap.Backend.Exception.RecordNotFoundException;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecordEntity;
import com.pathsnap.Backend.PhotoRecord.Repository.PhotoRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckPhotoRecord {

    private final PhotoRecordRepository photoRecordRepository;

    public PhotoRecordEntity exec(String photoRecordId) {
        return photoRecordRepository.findById(photoRecordId)
                .orElseThrow(() -> new RecordNotFoundException(photoRecordId));
    }
}
