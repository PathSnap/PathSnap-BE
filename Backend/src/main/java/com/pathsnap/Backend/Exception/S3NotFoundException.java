package com.pathsnap.Backend.Exception;

import com.pathsnap.Backend.Image.Dto.S3UploadReqDTO;
import org.springframework.web.multipart.MultipartFile;

// 이미지가 null이거나 비어있는지 확인
public class S3NotFoundException extends Exception {

    public static void validateImages(S3UploadReqDTO imageReqDTO) {
        if (imageReqDTO.getImages() == null || imageReqDTO.getImages().isEmpty()) {
            throw new IllegalArgumentException("No images provided.");
        }

        for (MultipartFile image : imageReqDTO.getImages()) {
            if (image.isEmpty()) {
                throw new IllegalArgumentException("Empty image file provided.");
            }
        }
    }
}
