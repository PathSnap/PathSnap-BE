package com.pathsnap.Backend.Image.Controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.pathsnap.Backend.Image.Dto.ImageReqDTO;
import com.pathsnap.Backend.Image.Dto.ImageResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final AmazonS3 amazonS3;

    @Value("${S3_BUCKET_NAME}")
    private String bucketName;

    @Autowired
    public ImageController(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    //S3 이미지 업로드
    @PostMapping
    public ResponseEntity<List<ImageResDTO>> uploadImages(@ModelAttribute ImageReqDTO imageReqDTO) {
        List<ImageResDTO> response = new ArrayList<>();

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
                String imageUrl = amazonS3.getUrl(bucketName, fileName).toString();
                response.add(new ImageResDTO("1", imageUrl));
            } catch (IOException e) {
                return ResponseEntity.internalServerError().body(null); // 500 Error 응답
            }

        }

        return ResponseEntity.ok(response); // 성공적인 응답
    }
}

