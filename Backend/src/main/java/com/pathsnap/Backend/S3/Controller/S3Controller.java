package com.pathsnap.Backend.S3.Controller;

import com.pathsnap.Backend.Exception.S3NotFoundException;
import com.pathsnap.Backend.S3.Dto.Res.S3ListResDTO;
import com.pathsnap.Backend.S3.Dto.Req.S3UpdateReqDTO;
import com.pathsnap.Backend.S3.Dto.Req.S3UploadReqDTO;
import com.pathsnap.Backend.S3.Dto.Res.S3ResDTO;
import com.pathsnap.Backend.S3.Service.S3Service;
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
    public ResponseEntity<S3ListResDTO> uploadImages(@ModelAttribute S3UploadReqDTO imageReqDTO) {
        try {
            List<S3ResDTO> uploadResponses = imageService.uploadImages(imageReqDTO);
            return ResponseEntity.ok(new S3ListResDTO(uploadResponses));
        } catch (IOException e) {
            throw new RuntimeException(e);  // 파일업로드 중 문제
        } catch (S3NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // 이미지 수정
    @PutMapping("/{imageId}")
    public ResponseEntity<S3ListResDTO> updateImage(@PathVariable String imageId,
                                                    @ModelAttribute S3UpdateReqDTO updateReqDTO) {
        try {
            List<S3ResDTO> updatedResponse = imageService.updateImages(imageId, updateReqDTO);
            return ResponseEntity.ok(new S3ListResDTO(updatedResponse));
        } catch (IOException e) {
            // 파일업로드 중 문제 발생
            throw new RuntimeException("Error occurred during file upload: " + e.getMessage());
        } catch (S3NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 이미지 삭제
    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable String imageId) {
        try {
            imageService.deleteImage(imageId);
            return ResponseEntity.ok().build(); 
        } catch (S3NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
