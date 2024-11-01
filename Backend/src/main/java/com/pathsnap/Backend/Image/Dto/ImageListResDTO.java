package com.pathsnap.Backend.Image.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ImageListResDTO {
    private List<S3UploadResDTO> images;

}
