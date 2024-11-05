package com.pathsnap.Backend.RouteRecord.Dto.Res;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouteUpdateResDto {
    private String routeId;
    private Number newSeq;
}
