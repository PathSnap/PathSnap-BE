package com.pathsnap.Backend.RouteRecord.Component;

import com.pathsnap.Backend.Exception.RouteRecordNotFoundException;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecordEntity;
import com.pathsnap.Backend.RouteRecord.Repository.RouteRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckRouteRecord {
    private final RouteRecordRepository routeRecordRepository;
    public RouteRecordEntity exec(String routeRecordId) {
        return routeRecordRepository.findById(routeRecordId)
                .orElseThrow(() -> new RouteRecordNotFoundException(routeRecordId));
    }
}
