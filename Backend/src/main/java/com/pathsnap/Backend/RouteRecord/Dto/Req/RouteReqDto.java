package com.pathsnap.Backend.RouteRecord.Dto.Req;

import com.pathsnap.Backend.Coordinate.Dto.Req.CoordinateReqDto;
import lombok.Data;

@Data
public class RouteReqDto {
    private String routeId;
    private CoordinateReqDto coordinateReqDto;

}
