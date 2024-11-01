package com.pathsnap.Backend.Image.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class S3ResDTO {
    private String imageId;
    private String url;

    public S3UploadResDTO(String imageId, String url) {
        this.imageId = imageId;
        this.url = url;
    }

}
