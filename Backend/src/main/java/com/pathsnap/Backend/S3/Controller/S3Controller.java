package com.pathsnap.Backend.S3.Controller;

import com.pathsnap.Backend.S3.Dto.Res.S3ListResDto;
import com.pathsnap.Backend.S3.Dto.Req.S3UploadReqDto;
import com.pathsnap.Backend.S3.Service.DeleteS3Service;
import com.pathsnap.Backend.S3.Service.CreateS3Service;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class S3Controller {

    private final CreateS3Service s3UploadService;

    private final DeleteS3Service deleteService;

    //S3 이미지 업로드
    @Operation(summary = "S3 이미지 업로드", description = "이미지 파일을 업로드하여 imageId와 url 전달")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<S3ListResDto>> uploadImage(@ModelAttribute S3UploadReqDto imageReqDTO) {
        return ResponseEntity.ok(s3UploadService.uploadImages(imageReqDTO));
    }


    // 이미지 삭제
    @Operation(summary = "S3 이미지 삭제", description = "S3와 이미지테이블 필드 삭제")
    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable String imageId) {
        deleteService.deleteImage(imageId);
        return ResponseEntity.ok().build();
    }


}

