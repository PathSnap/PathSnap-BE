package com.pathsnap.Backend.Image.Dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageResDTO {
    private String imageId;
    private String imageUrl;

    public ImageResDTO(String imageId, String imageUrl) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
    }

}
