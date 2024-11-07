package com.pathsnap.Backend.RouteRecord.Service;

import com.pathsnap.Backend.Exception.RecordNotFoundException;
import com.pathsnap.Backend.Record.Entity.RecordEntity;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
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

    private final RecordRepository recordRepository;
    private final RouteRecordRepository routeRecordRepository;

    public String startRoute(String recordId){
        RecordEntity record = recordRepository.findById(recordId)
                .orElseThrow(() -> new RecordNotFoundException(recordId));

        RouteRecordEntity RouteRecord = new RouteRecordEntity();
        RouteRecord.setRouteId(UUID.randomUUID().toString());
        RouteRecord.setRecord(record);

        Date startDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        RouteRecord.setStartDate(startDate);

        RouteRecord = routeRecordRepository.save(RouteRecord);

        return RouteRecord.getRouteId();
    }
}

