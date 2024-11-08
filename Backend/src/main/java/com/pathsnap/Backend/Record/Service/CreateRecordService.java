package com.pathsnap.Backend.Record.Service;

import com.pathsnap.Backend.Record.Component.CreateRecord;
import com.pathsnap.Backend.Record.Dto.Res.RecordStartDTO;
import com.pathsnap.Backend.Record.Entity.RecordEntity;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import com.pathsnap.Backend.User.Compnent.UserCheck;
import com.pathsnap.Backend.User.Entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateRecordService {

    private final RecordRepository recordRepository;
    private final CreateRecord createRecord;
    private final UserCheck userCheck;


    public RecordStartDTO startNewRecord(String userId,boolean recordIsGroup) {

        //userId가 있는지 확인
        UserEntity user = userCheck.exec(userId);

        //record 생성
        RecordEntity record = createRecord.exec(user,recordIsGroup);
        recordRepository.save(record);

        return RecordStartDTO.builder()
                .recordId(record.getRecordId())
                .build();
    }
}
