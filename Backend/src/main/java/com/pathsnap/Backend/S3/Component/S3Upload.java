package com.pathsnap.Backend.S3.Component;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class S3Upload {

    private final AmazonS3 amazonS3;

    @Value("${S3_BUCKET_NAME}")
    private String bucketName;

    public String exec(MultipartFile image) {
        String fileName = (int)(Math.random() * 100000) + "_" + System.currentTimeMillis();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(image.getSize());

        try {
            amazonS3.putObject(bucketName, fileName, image.getInputStream(), metadata);
        } catch (IOException e) {
            throw new RuntimeException("파일 업로드 중 오류가 발생했습니다.", e);
        }

        return amazonS3.getUrl(bucketName, fileName).toString();  // S3 URL 반환
    }
}
