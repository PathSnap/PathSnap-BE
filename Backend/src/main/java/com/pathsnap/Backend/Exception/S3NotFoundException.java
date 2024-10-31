package com.pathsnap.Backend.Exception;

import com.pathsnap.Backend.Image.Dto.S3UploadReqDTO;
import org.springframework.web.multipart.MultipartFile;

public class S3NotFoundException extends Exception {

    public S3NotFoundException(String message) {
        super(message);
    }

    // 이미지 유효성 검사 (null이거나 비어있는지 확인)
    public static void validateImages(S3UploadReqDTO imageReqDTO) throws S3NotFoundException {
        if (imageReqDTO.getImages() == null || imageReqDTO.getImages().isEmpty()) {
            throw new S3NotFoundException("No images provided.");
        }

        for (MultipartFile image : imageReqDTO.getImages()) {
            if (image.isEmpty()) {
                throw new S3NotFoundException("Empty image file provided.");
            }
        }
    }

    // 이미지 ID 유효성 검사
    public static void validateImageId(String imageId) throws S3NotFoundException {
        if (imageId == null || imageId.isEmpty()) {
            throw new S3NotFoundException("Invalid image ID provided.");
        }
    }
}
