package com.pathsnap.Backend.PhotoRecord.Component;

import com.pathsnap.Backend.Exception.RecordNotFoundException;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecord1Entity;
import com.pathsnap.Backend.PhotoRecord.Repository.PhotoRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CheckPhotoRecord {

    private final PhotoRecordRepository photoRecordRepository;

    public PhotoRecord1Entity exec(String photoRecordId) {
        return photoRecordRepository.findById(photoRecordId)
                .orElseThrow(() -> new RecordNotFoundException(photoRecordId));
    }

    public List<PhotoRecord1Entity> exec2(String recordId) {
        return photoRecordRepository.findByRecord_RecordId(recordId);
    }
}
