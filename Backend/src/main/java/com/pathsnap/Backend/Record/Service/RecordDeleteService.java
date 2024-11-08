package com.pathsnap.Backend.Record.Service;

import com.pathsnap.Backend.Record.Component.CheckRecord;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class RecordDeleteService {
    private final CheckRecord recordCheck;
    private final RecordRepository recordRepository;
    public void deleteRecord(String recordId){

        //기록ID 있는지 확인
        recordCheck.exec(recordId);
        //기록 삭제
        recordRepository.deleteById(recordId);

    }
}
