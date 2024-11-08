package com.pathsnap.Backend.RouteRecord.Component;

import com.pathsnap.Backend.Record.Entity.RecordEntity;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecordEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Component
public class CreateRouteRecord {

    public RouteRecordEntity exec(RecordEntity record) {

        // 현재 날짜를 Date 형식으로 변환
        Date startDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

        // 빌더를 사용하여 RouteRecordEntity 생성
        return RouteRecordEntity.builder()
                .routeId(UUID.randomUUID().toString())
                .record(record)
                .startDate(startDate)
                .seq(0)
                .build();
    }
}
