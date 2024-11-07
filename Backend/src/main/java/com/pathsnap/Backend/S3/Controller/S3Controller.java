package com.pathsnap.Backend.S3.Controller;

import com.pathsnap.Backend.S3.Dto.Res.S3ListResDto;
import com.pathsnap.Backend.S3.Dto.Req.S3UploadReqDto;
import com.pathsnap.Backend.S3.Service.S3DeleteService;
import com.pathsnap.Backend.S3.Service.S3CreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class S3Controller {

    private final S3CreateService s3UploadService;

    private final S3DeleteService deleteService;

    //S3 이미지 업로드
    @PostMapping
    public ResponseEntity<List<S3ListResDto>> uploadImages(@ModelAttribute S3UploadReqDto imageReqDTO) {
        return ResponseEntity.ok(s3UploadService.uploadImages(imageReqDTO));
    }


    // 이미지 삭제
    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable String imageId) {
        deleteService.deleteImage(imageId);
        return ResponseEntity.ok().build();
    }


}

