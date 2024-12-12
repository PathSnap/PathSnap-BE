package com.pathsnap.Backend.PackTrip.Dto.Req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UpdatePackTripReqDto {
    private String packTripId;
    private String packTripName;
    private List<String> dates;
}
