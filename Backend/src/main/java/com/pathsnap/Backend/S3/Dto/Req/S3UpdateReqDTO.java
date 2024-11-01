package com.pathsnap.Backend.S3.Dto.Req;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class S3UpdateReqDTO {
    private List<ImageUpdate> images;

    @Data
    public static class ImageUpdate {
        private String beforeUrl; // 이전 URL
        private MultipartFile newFile; // 새로운 파일
    }
}
