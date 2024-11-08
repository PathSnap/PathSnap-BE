package com.pathsnap.Backend.Coordinate.Component;

import com.pathsnap.Backend.Coordinate.Entitiy.Coordinate1Entity;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecord1Entity;
import org.springframework.stereotype.Component;

@Component
public class AddCoordinate {

    public void exec(RouteRecord1Entity routeRecord, Coordinate1Entity newCoordinate) {

        // 좌표를 경로에 추가
        routeRecord.addCoordinate(newCoordinate);
    }
}
