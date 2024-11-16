package com.pathsnap.Backend.Image.Dto.Res;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ImageResDto {
    private String imageId;
    private String url;
}
