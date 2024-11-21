package com.pathsnap.Backend.User.Dto.Res;

import com.pathsnap.Backend.Image.Dto.Res.ImageResDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LocationResDto {
    private List<LocationDto> locations;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class LocationDto {
        private String recordId;
        private String recordName;
        private ImageResDto images;
    }
}
