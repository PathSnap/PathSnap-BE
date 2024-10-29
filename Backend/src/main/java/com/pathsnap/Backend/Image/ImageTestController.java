package com.pathsnap.Backend.Image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageTestController {

    @Value("${DB_USERNAME}")
    private String accessKeyId;

    // accessKeyId 확인을 위한 테스트 엔드포인트
    @GetMapping("/check-access-key")
    public ResponseEntity<String> checkAccessKey() {
        return ResponseEntity.ok("Access Key ID: " + accessKeyId);
    }
}
