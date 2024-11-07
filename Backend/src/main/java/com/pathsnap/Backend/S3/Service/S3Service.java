package com.pathsnap.Backend.S3.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.pathsnap.Backend.Exception.S3NotFoundException;
import com.pathsnap.Backend.S3.Dto.Req.S3UploadReqDto;
import com.pathsnap.Backend.S3.Dto.Res.S3ListResDto;
import com.pathsnap.Backend.S3.Dto.Res.S3ResDto;
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
public class S3Service {

    private final AmazonS3 amazonS3;
    private final ImageRepository imageRepository;

    @Value("${S3_BUCKET_NAME}")
    private String bucketName;


    // S3 이미지 업로드
    public List<S3ListResDto> uploadImages(S3UploadReqDto imageReqDto) {
        List<S3ListResDto> response = new ArrayList<>();

        //유효성검사
        S3NotFoundException.validateImages(imageReqDto);

        List<S3ResDto> s3ResDtoList = new ArrayList<>();

        for (MultipartFile image : imageReqDto.getImages()) {
            if (image == null || image.isEmpty()) {
                throw new IllegalArgumentException("파일이 비어있습니다.");
            }


            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename(); // 이미지 ID 생성
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(image.getSize());
            // S3에 파일 업로드
            try {
                amazonS3.putObject(bucketName, fileName, image.getInputStream(), metadata);
            } catch (IOException e) {
                throw new RuntimeException("파일 업로드 중 오류가 발생했습니다.", e);
            }

            // 업로드한 이미지의 URL 생성
            String url = amazonS3.getUrl(bucketName, fileName).toString();

            // 이미지 정보를 데이터베이스에 저장
            ImageEntity newImage = new ImageEntity();
            String imageId = UUID.randomUUID().toString();
            newImage.setImageId(imageId);
            newImage.setUrl(url);
            newImage.setFileKey(fileName);
            imageRepository.save(newImage);

            // S3ResDto 객체 생성 후 리스트에 추가
            S3ResDto s3ResDto = new S3ResDto(imageId, url);
            s3ResDtoList.add(s3ResDto);
        }

        // S3ListResDto로 감싸서 response에 추가
        response.add(new S3ListResDto(s3ResDtoList));

        return response;
    }


    // S3 이미지 삭제
    public void deleteImage(String imageId) {
        // 유효성 검사
        S3NotFoundException.validateImageId(imageId);

        // 이미지 정보 가져오기 (Id가 없으면 예외처리)
        ImageEntity imageEntity = imageRepository.findById(imageId)
                .orElseThrow(() -> new IllegalArgumentException("Image not found"));

        // S3에서 이미지 삭제
        amazonS3.deleteObject(bucketName, imageEntity.getFileKey());

        // 데이터베이스에서 이미지 삭제
        imageRepository.delete(imageEntity);

    }


}
