package com.pathsnap.Backend.PhotoRecord.Dto.Res;

import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
@Builder
public class PhotoSummaryResDto {
    private String photoId;
    private String imagePhotoId;
    private String photoTitle;
    private String photoContent;
    private Date photoDate;
    private double lat;
    private double lng;
}
