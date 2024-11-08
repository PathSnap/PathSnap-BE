package com.pathsnap.Backend.Coordinate.Component;

import com.pathsnap.Backend.Coordinate.Dto.Req.CoordinateReqDto;
import com.pathsnap.Backend.Coordinate.Entitiy.Coordinate1Entity;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecord1Entity;
import org.springframework.stereotype.Component;

@Component
public class CreateCoordinate {
    public Coordinate1Entity exec(CoordinateReqDto coordinateReqDto, RouteRecord1Entity routeRecord) {

        //Coordinate1Entity 생성
        return Coordinate1Entity.builder()
                .lat(coordinateReqDto.getLat())
                .lng(coordinateReqDto.getLng())
                .timeStamp(coordinateReqDto.getTimeStamp())
                .routeRecord(routeRecord)
                .build();
    }
}
