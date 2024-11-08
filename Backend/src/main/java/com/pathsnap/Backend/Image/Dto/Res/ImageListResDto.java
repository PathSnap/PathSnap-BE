package com.pathsnap.Backend.Image.Dto.Res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ImageListResDto {
    private List<ImageResDto> images;
}