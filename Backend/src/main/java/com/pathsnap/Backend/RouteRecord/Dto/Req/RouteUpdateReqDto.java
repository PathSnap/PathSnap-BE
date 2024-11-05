package com.pathsnap.Backend.RouteRecord.Dto.Req;

import lombok.Data;

@Data
public class RouteUpdateReqDto {
    private String routeId;
    private int newSeq;
}
