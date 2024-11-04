package com.pathsnap.Backend.Coordinate.Dto.Req;

import lombok.Data;

import java.util.Date;

@Data
public class CoordinateReqDto {
    private double lat;
    private double lng;
    private Date timeStamp;
}
