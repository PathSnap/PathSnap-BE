package com.pathsnap.Backend.PhotoRecord.Dto.Res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PhotoDataResDto {
    private String recordId;
    private String photoId;
    private String url;
    private double lat;
    private double lng;

}
