package com.pathsnap.Backend.Image.Controller;

import com.pathsnap.Backend.Image.Dto.ImageReqDTO;
import com.pathsnap.Backend.Image.Dto.ImageResDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

    @PostMapping
    public ResponseEntity<List<ImageResDTO>> uploadImages(@ModelAttribute ImageReqDTO imageReqDTO) {
        List<ImageResDTO> imageResponses = new ArrayList<>();

        // 이미지가 null이거나 비어있는지 확인
        if (imageReqDTO.getImages() == null || imageReqDTO.getImages().isEmpty()) {
            return ResponseEntity.badRequest().body(null); // 400 Bad Request 응답
        }

        for (MultipartFile image : imageReqDTO.getImages()) {
            // 이미지 처리 로직 (S3 업로드 대신)
            if (image.isEmpty()) {
                return ResponseEntity.badRequest().body(null); // 비어있는 이미지 파일 처리
            }
            
            String imageId = System.currentTimeMillis() + "_" + image.getOriginalFilename(); // 이미지 ID 생성
            String imageUrl = "http://example.com/images/" + imageId; // 더미 URL

            imageResponses.add(new ImageResDTO(imageId, imageUrl)); // 응답 DTO에 추가
        }

        return ResponseEntity.ok(imageResponses); // 성공적인 응답
    }
}

