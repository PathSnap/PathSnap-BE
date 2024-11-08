package com.pathsnap.Backend.RouteRecord.Component;

import com.pathsnap.Backend.Coordinate.Dto.Res.CoordinateResDto;
import com.pathsnap.Backend.RouteRecord.Dto.Res.RouteRecordResDto;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecordEntity;
import com.pathsnap.Backend.Record.Component.CheckRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetRouteRecord {

    private final CheckRecord recordCheck;
    public List<RouteRecordResDto> exec(String recordId) {

        // recordId에 해당하는 RouteRecordEntity를 조회
        List<RouteRecordEntity> routeRecords = (List<RouteRecordEntity>) recordCheck.exec(recordId);

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
                            .coordinates(coordinateResDtos)
                            .seq(routeRecord.getSeq())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
