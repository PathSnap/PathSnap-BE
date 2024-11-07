package com.pathsnap.Backend.Record.Service;

import com.pathsnap.Backend.Exception.UserNotFoundException;
import com.pathsnap.Backend.Record.Dto.Res.RecordStartDTO;
import com.pathsnap.Backend.Record.Entity.RecordEntity;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import com.pathsnap.Backend.User.Entity.UserEntity;
import com.pathsnap.Backend.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Service
public class RecordStartService {

    @Autowired
    private RecordRepository recordRepository;
    @Autowired
    private UserRepository userRepository;

    public RecordStartDTO startNewRecord(String userId,boolean recordIsGroup) {

        // 유저가 존재하지 않을 겨우 예외를 발생
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        RecordEntity record = new RecordEntity();
        record.setRecordId(UUID.randomUUID().toString());
        record.setUser(user);
        record.setRecordName("여행 제목 없음");
        record.setRecordIsGroup(recordIsGroup);

        Date startDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        record.setStartDate(startDate);

        recordRepository.save(record);

        return RecordStartDTO.builder()
                .recordId(record.getRecordId())
                .build();
    }
}
