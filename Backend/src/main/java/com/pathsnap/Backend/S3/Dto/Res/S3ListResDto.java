package com.pathsnap.Backend.S3.Dto.Res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class S3ListResDto {
    private List<S3ResDto> images;

}
