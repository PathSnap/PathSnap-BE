package com.pathsnap.Backend.Record.Service;

import com.pathsnap.Backend.Exception.RecordNotFoundException;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class RecordDeleteService {

    private final RecordRepository RecordRepository;

    public void deleteRecord(String recordId){
        if(!RecordRepository.existsById(recordId)){
            throw new RecordNotFoundException(recordId);
        }
        RecordRepository.deleteById(recordId);

    }
}
