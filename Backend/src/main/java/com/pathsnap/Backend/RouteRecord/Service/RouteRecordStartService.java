package com.pathsnap.Backend.RouteRecord.Service;

import com.pathsnap.Backend.Record.Component.CheckRecord;
import com.pathsnap.Backend.Record.Entity.RecordEntity;
import com.pathsnap.Backend.RouteRecord.Dto.Res.RouteRecordStartDto;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecordEntity;
import com.pathsnap.Backend.RouteRecord.Repository.RouteRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RouteRecordStartService {

    private final CheckRecord recordCheck;
    private final RouteRecordRepository routeRecordRepository;

    public RouteRecordStartDto startRoute(String recordId){

        //기록ID 있는지 확인
        RecordEntity record = recordCheck.exec(recordId);

        //경로기록 시작 저장
        RouteRecordEntity RouteRecord = new RouteRecordEntity();
        RouteRecord.setRouteId(UUID.randomUUID().toString());
        RouteRecord.setRecord(record);

        Date startDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        RouteRecord.setStartDate(startDate);

        routeRecordRepository.save(RouteRecord);

        return RouteRecordStartDto.builder()
                .routeId(RouteRecord.getRouteId())
                .build();
    }
}

