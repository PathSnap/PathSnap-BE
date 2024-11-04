package com.pathsnap.Backend.PackTrip.Dto.Req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PackTripReqDTO {
    private String packTripName;
    private List<String> dates;
}
