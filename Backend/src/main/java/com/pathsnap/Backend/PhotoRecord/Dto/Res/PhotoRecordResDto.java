package com.pathsnap.Backend.PhotoRecord.Dto.Res;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pathsnap.Backend.Image.Dto.Req.ImageReqDto;
import com.pathsnap.Backend.Image.Dto.Res.ImageResDto;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class PhotoRecordResDto {
    private String photoId;
    private Number seq;
    private List<ImageResDto> images;
    private String photoTitle;
    private String photoLocation;
    private String photoContent;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date photoDate;

    private double lat;
    private double lng;

}