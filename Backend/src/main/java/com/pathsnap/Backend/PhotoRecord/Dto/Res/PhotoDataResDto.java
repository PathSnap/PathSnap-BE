package com.pathsnap.Backend.PhotoRecord.Dto.Res;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhotoDataResDto {
    private String photoId;
    private String url;
    private double lat;
    private double lng;

}
