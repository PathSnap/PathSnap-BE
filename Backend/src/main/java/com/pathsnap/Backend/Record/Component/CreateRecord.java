package com.pathsnap.Backend.Record.Component;

import com.pathsnap.Backend.Record.Entity.Record1Entity;
import com.pathsnap.Backend.User.Entity.User1Entity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Component
public class CreateRecord {
    public Record1Entity exec(User1Entity user, boolean recordIsGroup) {
        return Record1Entity.builder()
                .recordId(UUID.randomUUID().toString())
                .user(user)
                .recordName("여행 제목 없음")
                .recordIsGroup(recordIsGroup)
                .startDate(LocalDateTime.now()) // 현재 날짜와 시간
                .build();
    }
}
