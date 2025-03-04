package com.pathsnap.Backend.RouteRecord.Component;

import com.pathsnap.Backend.Coordinate.Dto.Res.CoordinateResDto;
import com.pathsnap.Backend.RouteRecord.Dto.Res.RouteRecordResDto;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecord1Entity;
import com.pathsnap.Backend.Record.Component.CheckRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetRouteRecord {

    private final CheckRouteRecord recordCheck;
    public List<RouteRecordResDto> exec(String recordId) {

        // recordId에 해당하는 RouteRecordEntity를 조회
        List<RouteRecord1Entity> routeRecords = recordCheck.exec2(recordId);

        // RouteRecordEntity를 RouteRecordResDto로 변환
        return routeRecords.stream()
                .map(routeRecord -> {
                    List<CoordinateResDto> coordinateResDtos = routeRecord.getCoordinates()
                            .stream()
                            .map(coordinate -> CoordinateResDto.builder()
                                    .lat(coordinate.getLat())
                                    .lng(coordinate.getLng())
                                    .timeStamp(coordinate.getTimeStamp())
                                    .build())
                            .collect(Collectors.toList());

                    return RouteRecordResDto.builder()
                            .routeId(routeRecord.getRouteId())
                            .startDate(coordinateResDtos.get(0).getTimeStamp().toString())
                            .endDate(coordinateResDtos.get(coordinateResDtos.size()-1).getTimeStamp().toString())
                            .transportMode(routeRecord.getTransportMode())
                            .coordinates(coordinateResDtos)
                            .seq(routeRecord.getSeq())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
