package com.pathsnap.Backend.RouteRecord.Component;

import com.pathsnap.Backend.Record.Entity.Record1Entity;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecord1Entity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Component
public class CreateRouteRecord {

    public RouteRecord1Entity exec(Record1Entity record, int seq) {

        // 현재 날짜를 Date 형식으로 변환
        Date startDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

        // 빌더를 사용하여 RouteRecord1Entity 생성
        return RouteRecord1Entity.builder()
                .routeId(UUID.randomUUID().toString())
                .record(record)
                .startDate(startDate)
                .seq(seq)
                .build();
    }
}
