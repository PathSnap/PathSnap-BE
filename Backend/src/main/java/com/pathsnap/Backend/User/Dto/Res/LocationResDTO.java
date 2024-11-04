package com.pathsnap.Backend.User.Dto.Res;

import com.pathsnap.Backend.Image.Dto.Res.ImageListResDTO;
import com.pathsnap.Backend.S3.Dto.Res.S3ListResDTO;
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
        private ImageListResDTO images;
    }
}
