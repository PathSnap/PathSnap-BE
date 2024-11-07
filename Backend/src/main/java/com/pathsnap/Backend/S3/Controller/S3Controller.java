package com.pathsnap.Backend.S3.Controller;

import com.pathsnap.Backend.Exception.S3NotFoundException;
import com.pathsnap.Backend.S3.Dto.Res.S3ListResDto;
import com.pathsnap.Backend.S3.Dto.Req.S3UpdateReqDto;
import com.pathsnap.Backend.S3.Dto.Req.S3UploadReqDto;
import com.pathsnap.Backend.S3.Dto.Res.S3ResDto;
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
    public List<S3ListResDto> uploadImages(@ModelAttribute S3UploadReqDto imageReqDTO) {
        return imageService.uploadImages(imageReqDTO);
    }


    // 이미지 삭제
    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable String imageId) {
        imageService.deleteImage(imageId);
        return ResponseEntity.ok().build();
    }


}

