package com.pathsnap.Backend.Exception;

import com.pathsnap.Backend.S3.Dto.Req.S3UploadReqDto;
import org.springframework.web.multipart.MultipartFile;

public class S3NotFoundException extends Exception {

//    public S3NotFoundException(String message) {
//        super(message);
//    }

    // 이미지 유효성 검사 (null이거나 비어있는지 확인)
    public static void validateImages(S3UploadReqDto imageReqDTO){
        if (imageReqDTO.getImages() == null || imageReqDTO.getImages().isEmpty()) {
            throw new IllegalArgumentException("No images provided.");
        }

        for (MultipartFile image : imageReqDTO.getImages()) {
            if (image.isEmpty()) {
                throw new IllegalArgumentException("Empty image file provided.");
            }
        }
    }

    // 이미지 ID 유효성 검사
    public static void validateImageId(String imageId) {
        if (imageId == null || imageId.isEmpty()) {
            throw new IllegalArgumentException("Invalid image ID provided.");
        }
    }
}
