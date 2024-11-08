package com.pathsnap.Backend.Coordinate.Component;

import com.pathsnap.Backend.Coordinate.Entitiy.CoordinateEntity;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecordEntity;
import org.springframework.stereotype.Component;

@Component
public class AddCoordinate {

    public void exec(RouteRecordEntity routeRecord, CoordinateEntity newCoordinate) {

        // 좌표를 경로에 추가
        routeRecord.addCoordinate(newCoordinate);
    }
}
