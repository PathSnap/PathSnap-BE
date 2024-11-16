package com.pathsnap.Backend.RouteRecord.Dto.Res;

import com.pathsnap.Backend.Coordinate.Dto.Res.CoordinateResDto;
import com.pathsnap.Backend.RouteRecord.Entity.TransportMode;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class RouteRecordResDto {
    private String routeId;
    private Number seq;
    private TransportMode transportMode;
    private String startDate;
    private String endDate;
    private List<CoordinateResDto> coordinates;
}
