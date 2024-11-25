package com.pathsnap.Backend.S3.Controller;

import com.pathsnap.Backend.Record.Controller.RecordControllerDocs;
import com.pathsnap.Backend.S3.Dto.Res.S3ListResDto;
import com.pathsnap.Backend.S3.Dto.Req.S3UploadReqDto;
import com.pathsnap.Backend.S3.Service.DeleteS3Service;
import com.pathsnap.Backend.S3.Service.CreateS3Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class S3Controller implements S3ControllerDocs{

    private final CreateS3Service s3UploadService;

    private final DeleteS3Service deleteService;

    //S3 이미지 업로드
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<S3ListResDto> uploadImage(@ModelAttribute S3UploadReqDto imageReqDTO) {
        return ResponseEntity.ok(s3UploadService.uploadImages(imageReqDTO));
    }


    // 이미지 삭제
    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable String imageId) {
        deleteService.deleteImage(imageId);
        return ResponseEntity.ok().build();
    }


}

