package com.pathsnap.Backend.Image.Dto.Req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageReqDto {
    private String imageId;
    private String url;
}
