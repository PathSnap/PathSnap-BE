package com.pathsnap.Backend.Coordinate.Dto.Req;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.Data;

@Data
public class CoordinateReqDto {
    private double lat;
    private double lng;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date timeStamp;

}
