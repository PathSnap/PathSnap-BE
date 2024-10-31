package com.pathsnap.Backend.Image.Controller;

import com.pathsnap.Backend.Exception.S3NotFoundException;
import com.pathsnap.Backend.Exception.UserNotFoundException;
import com.pathsnap.Backend.Image.Dto.ImageListResDTO;
import com.pathsnap.Backend.Image.Dto.S3UpdateReqDTO;
import com.pathsnap.Backend.Image.Dto.S3UploadReqDTO;
import com.pathsnap.Backend.Image.Dto.S3ResDTO;
import com.pathsnap.Backend.Image.Service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class S3Controller {

    private final S3Service imageService;

    //S3 이미지 업로드
    @PostMapping
    public ResponseEntity<ImageListResDTO> uploadImages(@ModelAttribute S3UploadReqDTO imageReqDTO) {
        try {
            List<S3ResDTO> uploadResponses = imageService.uploadImages(imageReqDTO);
            return ResponseEntity.ok(new ImageListResDTO(uploadResponses)); // 성공적인 응답
        } catch (IOException e) {
            throw new RuntimeException(e);  // 파일업로드 중 문제
        }catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (S3NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // 이미지 수정
    @PutMapping("/{imageId}")
    public ResponseEntity<ImageListResDTO> updateImage(@PathVariable String imageId,
                                                       @ModelAttribute S3UpdateReqDTO updateReqDTO) {
        try {
            List<S3ResDTO> updatedResponse = imageService.updateImages(imageId, updateReqDTO);
            return ResponseEntity.ok(new ImageListResDTO(updatedResponse)); // 성공적인 응답
        } catch (IOException e) {
            // 파일업로드 중 문제 발생
            throw new RuntimeException("Error occurred during file upload: " + e.getMessage());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (S3NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}

