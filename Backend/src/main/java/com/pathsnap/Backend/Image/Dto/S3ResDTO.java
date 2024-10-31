package com.pathsnap.Backend.Image.Dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class S3ResDTO {
    private String imageId;
    private String url;

    public S3ResDTO(String imageId, String url) {
        this.imageId = imageId;
        this.url = url;
    }

}
