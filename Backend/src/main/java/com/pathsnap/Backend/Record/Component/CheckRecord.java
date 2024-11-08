package com.pathsnap.Backend.Record.Component;

import com.pathsnap.Backend.Exception.RecordNotFoundException;
import com.pathsnap.Backend.Record.Entity.RecordEntity;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckRecord {

    private final RecordRepository recordRepository;

    public RecordEntity exec(String recordId) {
        return recordRepository.findById(recordId)
                .orElseThrow(() -> new RecordNotFoundException(recordId));
    }
}
