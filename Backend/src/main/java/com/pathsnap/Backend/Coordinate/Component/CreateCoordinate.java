package com.pathsnap.Backend.Coordinate.Component;

import com.pathsnap.Backend.Coordinate.Dto.Req.CoordinateReqDto;
import com.pathsnap.Backend.Coordinate.Entitiy.CoordinateEntity;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecordEntity;
import org.springframework.stereotype.Component;

@Component
public class CreateCoordinate {
    public CoordinateEntity exec(CoordinateReqDto coordinateReqDto, RouteRecordEntity routeRecord) {

        //CoordinateEntity 생성
        return CoordinateEntity.builder()
                .lat(coordinateReqDto.getLat())
                .lng(coordinateReqDto.getLng())
                .timeStamp(coordinateReqDto.getTimeStamp())
                .routeRecord(routeRecord)
                .build();
    }
}
