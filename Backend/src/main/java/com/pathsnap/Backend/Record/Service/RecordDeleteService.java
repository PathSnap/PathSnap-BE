package com.pathsnap.Backend.Record.Service;

import com.pathsnap.Backend.Exception.RecordNotFoundException;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Builder
public class RecordDeleteService {
    @Autowired
    RecordRepository RecordRepository;

    public void deleteRecord(String recordId){
        if(!RecordRepository.existsById(recordId)){
            throw new RecordNotFoundException(recordId);
        }
        RecordRepository.deleteById(recordId);

    }
}
