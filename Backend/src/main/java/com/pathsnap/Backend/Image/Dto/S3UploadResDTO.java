package com.pathsnap.Backend.Image.Dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class S3UploadResDTO {
    private String imageId;
    private String url;

    public S3UploadResDTO(String imageId, String url) {
        this.imageId = imageId;
        this.url = url;
    }

}
