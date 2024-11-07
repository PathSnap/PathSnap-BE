package com.pathsnap.Backend.S3.Dto.Req;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
public class S3UploadReqDto {
    private List<MultipartFile> images;


}
