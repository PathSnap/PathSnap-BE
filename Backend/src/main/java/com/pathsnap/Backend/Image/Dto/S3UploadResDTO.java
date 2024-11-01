package com.pathsnap.Backend.Image.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class S3UploadResDTO {
    private String imageId;
    private String url;

}
