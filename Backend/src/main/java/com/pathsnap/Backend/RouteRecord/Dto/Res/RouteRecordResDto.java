package com.pathsnap.Backend.RouteRecord.Dto.Res;

import com.pathsnap.Backend.Coordinate.Dto.Res.CoordinateResDto;
import lombok.Data;

import java.util.List;

@Data
public class RouteRecordResDto {
    private String routeId;
    private int seq;
    private String transportMode;
    private String startDate;
    private String endDate;
    private List<CoordinateResDto> coordinates;
}
