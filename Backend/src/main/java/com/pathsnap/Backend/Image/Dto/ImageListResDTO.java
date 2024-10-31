package com.pathsnap.Backend.Image.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ImageListResDTO {
    private List<S3ResDTO> images;

    public ImageListResDTO(List<S3ResDTO> images) {
        this.images = images;
    }
}