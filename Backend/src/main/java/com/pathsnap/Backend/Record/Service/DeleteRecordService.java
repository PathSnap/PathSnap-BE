package com.pathsnap.Backend.Record.Service;

import com.pathsnap.Backend.Record.Component.CheckRecord;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class DeleteRecordService {

    private final CheckRecord checkRecord;
    private final RecordRepository recordRepository;

    public void deleteRecord(String recordId){

        //recordId 있는지 확인
        checkRecord.exec(recordId);

        //record 삭제
        recordRepository.deleteById(recordId);

    }
}
