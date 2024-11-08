package com.pathsnap.Backend.PhotoRecord.Dto.Req;

import com.pathsnap.Backend.Coordinate.Dto.Req.CoordinateReqDto;
import com.pathsnap.Backend.Image.Dto.Req.ImageReqDto;
import com.pathsnap.Backend.Image.Entity.ImageEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PhotoRecordReqDto {

    private String photoId;
    private int seq;
    private List<ImageReqDto> images = new ArrayList<>();
    private String photoTitle;
    private String photoContent;
    private String photoDate;
    private double lat;
    private double lng;

}

