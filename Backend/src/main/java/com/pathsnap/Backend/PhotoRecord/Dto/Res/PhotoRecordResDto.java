package com.pathsnap.Backend.PhotoRecord.Dto.Res;

import com.pathsnap.Backend.Image.Dto.Req.ImageReqDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PhotoRecordResDto {
    private String photoId;
    private Number seq;
    private List<ImageReqDto> images;
    private String photoTitle;
    private String photoContent;
    private String photoDate;
    private double lat;
    private double lng;

}