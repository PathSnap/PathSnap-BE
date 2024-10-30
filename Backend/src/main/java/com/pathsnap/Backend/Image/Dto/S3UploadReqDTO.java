package com.pathsnap.Backend.Image.Dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
public class S3UploadReqDTO {
    private List<MultipartFile> images;

    // Getter and Setter
    public List<MultipartFile> getImages() {
        return images;
    }

}
