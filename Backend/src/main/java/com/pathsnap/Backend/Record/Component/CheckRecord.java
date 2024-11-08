package com.pathsnap.Backend.Record.Component;

import com.pathsnap.Backend.Exception.RecordNotFoundException;
import com.pathsnap.Backend.Record.Entity.Record1Entity;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import com.pathsnap.Backend.User.Compnent.UserCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CheckRecord {

    private final RecordRepository recordRepository;
    private final UserCheck userCheck;

    public Record1Entity exec(String recordId) {
        return recordRepository.findById(recordId)
                .orElseThrow(() -> new RecordNotFoundException(recordId));
    }

    public List<String> exec2(String userId) {
        return recordRepository.findByUser(userCheck.exec(userId))
                .stream()
                .map(Record1Entity::getRecordId)
                .collect(Collectors.toList());
    }
}
