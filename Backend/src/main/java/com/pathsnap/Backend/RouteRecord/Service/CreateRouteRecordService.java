package com.pathsnap.Backend.RouteRecord.Service;

import com.pathsnap.Backend.Record.Component.CheckRecord;
import com.pathsnap.Backend.Record.Entity.Record1Entity;
import com.pathsnap.Backend.RouteRecord.Component.CreateRouteRecord;
import com.pathsnap.Backend.RouteRecord.Dto.Res.RouteRecordStartDto;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecord1Entity;
import com.pathsnap.Backend.RouteRecord.Repository.RouteRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateRouteRecordService {

    private final CheckRecord recordCheck;
    private final CreateRouteRecord createRouteRecord;
    private final RouteRecordRepository routeRecordRepository;

    public RouteRecordStartDto startRoute(String recordId, int seq){

        //recordId 있는지 확인
        Record1Entity record = recordCheck.exec(recordId);

        //routeRecord 생성
        RouteRecord1Entity routeRecord= createRouteRecord.exec(record, seq);

        //routeRecord 저장
        routeRecordRepository.save(routeRecord);

        return RouteRecordStartDto.builder()
                .routeId(routeRecord.getRouteId())
                .build();
    }
}

