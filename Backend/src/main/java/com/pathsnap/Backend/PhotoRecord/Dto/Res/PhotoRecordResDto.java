package com.pathsnap.Backend.PhotoRecord.Dto.Res;

import com.pathsnap.Backend.Coordinate.Dto.Req.CoordinateReqDto;
import com.pathsnap.Backend.Image.Dto.Req.ImageReqDto;
import com.pathsnap.Backend.Image.Dto.Res.ImageResDTO;
import com.pathsnap.Backend.Image.Entity.ImageEntity;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class PhotoRecordResDto {
    private String photoId;
    private int seq;
    private List<ImageReqDto> images;
    private String photoTitle;
    private String photoContent;
    private String photoDate;
    private double lat;
    private double lng;

}