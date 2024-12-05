package com.pathsnap.Backend.PhotoRecord.Dto.Req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pathsnap.Backend.Image.Dto.Req.ImageReqDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PhotoRecordEditReqDto {

    private String photoId;
    private int seq;
    private List<ImageReqDto> images = new ArrayList<>();
    private String photoTitle;
    private String photoLocation;
    private String photoContent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date photoDate;
    private double lat;
    private double lng;

}

