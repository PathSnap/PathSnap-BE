package com.pathsnap.Backend.S3.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.pathsnap.Backend.Image.Entity.ImageEntity;
import com.pathsnap.Backend.Image.Repository.ImageRepository;
import com.pathsnap.Backend.Image.Component.CheckImage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteS3Service {

    private final AmazonS3 amazonS3;
    private final ImageRepository imageRepository;
    private final CheckImage imageCheck;

    @Value("${S3_BUCKET_NAME}")
    private String bucketName;


    // S3 이미지 삭제
    public void deleteImage(String imageId) {

        // 이미지 정보 가져오기
        ImageEntity imageEntity = imageCheck.exec(imageId);

        // S3에서 이미지 삭제
        amazonS3.deleteObject(bucketName, imageEntity.getFileKey());

        // 데이터베이스에서 이미지 삭제
        imageRepository.delete(imageEntity);

    }


}
