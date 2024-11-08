package com.pathsnap.Backend.Coordinate.Component;

import com.pathsnap.Backend.Coordinate.Entitiy.CoordinateEntity;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecord1Entity;
import org.springframework.stereotype.Component;

@Component
public class AddCoordinate {

    public void exec(RouteRecord1Entity routeRecord, CoordinateEntity newCoordinate) {

        // 좌표를 경로에 추가
        routeRecord.addCoordinate(newCoordinate);
    }
}
