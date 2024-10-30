package com.pathsnap.Backend.Image.Controller;

import com.pathsnap.Backend.Exception.UserNotFoundException;
import com.pathsnap.Backend.Image.Dto.ImageListResDTO;
import com.pathsnap.Backend.Image.Dto.S3UploadReqDTO;
import com.pathsnap.Backend.Image.Dto.S3UploadResDTO;
import com.pathsnap.Backend.Image.Service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    //S3 이미지 업로드
    @PostMapping
    public ResponseEntity<ImageListResDTO> uploadImages(@ModelAttribute S3UploadReqDTO imageReqDTO) {
        try {
            List<S3UploadResDTO> uploadResponses = imageService.uploadImages(imageReqDTO);
            return ResponseEntity.ok(new ImageListResDTO(uploadResponses)); // 성공적인 응답
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); //null 확인
        } catch (IOException e) {
            throw new RuntimeException(e);  // 파일업로드 중 문제
        }catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

