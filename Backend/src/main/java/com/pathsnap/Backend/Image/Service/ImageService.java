package com.pathsnap.Backend.Image.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.pathsnap.Backend.Exception.S3NotFoundException;
import com.pathsnap.Backend.Image.Dto.S3UploadReqDTO;
import com.pathsnap.Backend.Image.Dto.S3UploadResDTO;
import com.pathsnap.Backend.Image.Entity.ImageEntity;
import com.pathsnap.Backend.Image.Repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final AmazonS3 amazonS3;
    private final ImageRepository imageRepository;

    @Value("${S3_BUCKET_NAME}")
    private String bucketName;

    public List<S3UploadResDTO> uploadImages(S3UploadReqDTO imageReqDTO) throws IOException {
        List<S3UploadResDTO> response = new ArrayList<>();

        //예외처리
        S3NotFoundException.validateImages(imageReqDTO);

        for (MultipartFile image : imageReqDTO.getImages()) {


            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename(); // 이미지 ID 생성
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(image.getSize());
            amazonS3.putObject(bucketName, fileName, image.getInputStream(), metadata);

            // 업로드한 이미지의 URL 생성
            String url = amazonS3.getUrl(bucketName, fileName).toString();

            // 이미지 정보를 데이터베이스에 저장
            ImageEntity newImage = new ImageEntity();
            String imageId = UUID.randomUUID().toString();
            newImage.setImageId(imageId);
            newImage.setUrl(url);
            imageRepository.save(newImage);

            response.add(new S3UploadResDTO(imageId, url));
        }

        return response; // 응답 리스트 반환
    }
}
