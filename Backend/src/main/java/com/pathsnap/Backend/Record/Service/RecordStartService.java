package com.pathsnap.Backend.Record.Service;

import com.pathsnap.Backend.Record.Component.SaveRecordStart;
import com.pathsnap.Backend.Record.Dto.Res.RecordStartDTO;
import com.pathsnap.Backend.Record.Entity.RecordEntity;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import com.pathsnap.Backend.User.Compnent.UserCheck;
import com.pathsnap.Backend.User.Entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecordStartService {

    private final RecordRepository recordRepository;
    private final SaveRecordStart saveRecordStart;
    private final UserCheck userCheck;


    public RecordStartDTO startNewRecord(String userId,boolean recordIsGroup) {

        //유저ID가 있는지 확인
        UserEntity user = userCheck.exec(userId);

        //기록시작 저장
        RecordEntity record = saveRecordStart.exec(user,recordIsGroup);
        recordRepository.save(record);

        return RecordStartDTO.builder()
                .recordId(record.getRecordId())
                .build();
    }
}
