package com.pathsnap.Backend.Record.Component;

import com.pathsnap.Backend.Exception.RecordNotFoundException;
import com.pathsnap.Backend.Record.Entity.RecordEntity;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetRecord {

    @Autowired
    private RecordRepository recordRepository;

    public RecordEntity findByRecordId(String recordId) {
        return recordRepository.findById(recordId)
                .orElseThrow(() -> new RecordNotFoundException(recordId));
    }
}
