package com.pathsnap.Backend.RouteRecord.Component;

import com.pathsnap.Backend.Exception.RouteRecordNotFoundException;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecord1Entity;
import com.pathsnap.Backend.RouteRecord.Repository.RouteRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CheckRouteRecord {
    private final RouteRecordRepository routeRecordRepository;
    public RouteRecord1Entity exec(String routeRecordId) {
        return routeRecordRepository.findById(routeRecordId)
                .orElseThrow(() -> new RouteRecordNotFoundException(routeRecordId));
    }

    public List<RouteRecord1Entity> exec2(String recordId) {
        return routeRecordRepository.findByRecordIdWithCoordinates(recordId);
    }
}
