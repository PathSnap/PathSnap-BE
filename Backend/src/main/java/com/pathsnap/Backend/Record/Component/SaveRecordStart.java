package com.pathsnap.Backend.Record.Component;

import com.pathsnap.Backend.Record.Entity.RecordEntity;
import com.pathsnap.Backend.User.Entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Component
public class SaveRecordStart {
    public RecordEntity exec(UserEntity user, boolean recordIsGroup) {
        return RecordEntity.builder()
                .recordId(UUID.randomUUID().toString())
                .user(user)
                .recordName("여행 제목 없음")
                .recordIsGroup(recordIsGroup)
                .startDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
    }
}
