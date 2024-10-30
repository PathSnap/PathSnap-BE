package com.pathsnap.Backend.Image.Controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.pathsnap.Backend.Image.Dto.ImageListResDTO;
import com.pathsnap.Backend.Image.Dto.S3UploadReqDTO;
import com.pathsnap.Backend.Image.Dto.S3UploadResDTO;
import com.pathsnap.Backend.Image.Entity.ImageEntity;
import com.pathsnap.Backend.Image.Repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {

    private final AmazonS3 amazonS3;
    private final ImageRepository imageRepository;

    @Value("${S3_BUCKET_NAME}")
    private String bucketName;

    //S3 이미지 업로드
    @PostMapping
    public ResponseEntity<ImageListResDTO> uploadImages(@ModelAttribute S3UploadReqDTO imageReqDTO) {
        List<S3UploadResDTO> response = new ArrayList<>();

        // 이미지가 null이거나 비어있는지 확인
        if (imageReqDTO.getImages() == null || imageReqDTO.getImages().isEmpty()) {
            return ResponseEntity.badRequest().body(null); // 400 Bad Request 응답
        }

        for (MultipartFile image : imageReqDTO.getImages()) {
            if (image.isEmpty()) {
                return ResponseEntity.badRequest().body(null); // 비어있는 이미지 파일 처리
            }

            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename(); // 이미지 ID 생성
            try {
                // 이미지 파일을 S3에 업로드
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(image.getSize());
                amazonS3.putObject(bucketName, fileName, image.getInputStream(), metadata);

                // 업로드한 이미지의 URL 생성
                String url = amazonS3.getUrl(bucketName, fileName).toString();

                // 이미지 정보를 데이터베이스에 저장
                ImageEntity newImage = new ImageEntity();
                String imageId = UUID.randomUUID().toString();
                newImage.setImageId(imageId);
                newImage.setUrl(url);
                imageRepository.save(newImage); // 저장

                response.add(new S3UploadResDTO(imageId, url));
            } catch (IOException e) {
                return ResponseEntity.internalServerError().body(null); // 500 Error 응답
            }

        }

        return ResponseEntity.ok(new ImageListResDTO(response)); // 성공적인 응답
    }
}

