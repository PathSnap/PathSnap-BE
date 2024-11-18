package com.pathsnap.Backend.Record.Component;

import com.pathsnap.Backend.Exception.RecordNotFoundException;
import com.pathsnap.Backend.Record.Entity.Record1Entity;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetRecord {
    private final RecordRepository recordRepository;

    public Record1Entity WithUserAndFriends(String recordId) {
        return recordRepository.findByIdWithUserAndFriends(recordId)
                .orElseThrow(() -> new RecordNotFoundException(recordId));
    }
}
