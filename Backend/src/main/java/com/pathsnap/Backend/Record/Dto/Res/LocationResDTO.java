package com.pathsnap.Backend.Record.Dto.Res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LocationResDTO {
    private List<LocationDTO> locations;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class LocationDTO {
        private String recordId;
        private String recordName;
    }
}
