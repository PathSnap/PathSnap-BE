package com.pathsnap.Backend.RouteRecord.Service;

import com.pathsnap.Backend.Record.Component.CheckRecord;
import com.pathsnap.Backend.Record.Entity.RecordEntity;
import com.pathsnap.Backend.RouteRecord.Component.CreateRouteRecord;
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
public class CreateRouteRecordService {

    private final CheckRecord recordCheck;
    private final CreateRouteRecord createRouteRecord;
    private final RouteRecordRepository routeRecordRepository;

    public RouteRecordStartDto startRoute(String recordId){

        //recordId 있는지 확인
        RecordEntity record = recordCheck.exec(recordId);

        //routeRecord 생성
        RouteRecordEntity routeRecord= createRouteRecord.exec(record);

        //routeRecord 저장
        routeRecordRepository.save(routeRecord);

        return RouteRecordStartDto.builder()
                .routeId(routeRecord.getRouteId())
                .build();
    }
}

