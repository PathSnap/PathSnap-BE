package com.pathsnap.Backend.S3.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.pathsnap.Backend.Exception.S3NotFoundException;
import com.pathsnap.Backend.S3.Dto.Req.S3UpdateReqDto;
import com.pathsnap.Backend.S3.Dto.Req.S3UploadReqDto;
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
    public List<S3ResDto> uploadImages(S3UploadReqDto imageReqDto) throws IOException, S3NotFoundException{
        List<S3ResDto> response = new ArrayList<>();

        //유효성검사
        S3NotFoundException.validateImages(imageReqDto);

        for (MultipartFile image : imageReqDto.getImages()) {


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
            newImage.setFileKey(fileName);
            imageRepository.save(newImage);

            response.add(new S3ResDto(imageId, url));
        }

        return response;
    }

    // S3 이미지 수정
    public List<S3ResDto> updateImages(String imageId, S3UpdateReqDto imageReqDto) throws IOException, S3NotFoundException {

        // 유효성 검사
        S3NotFoundException.validateImageId(imageId);

        List<S3ResDto> response = new ArrayList<>();

        // 기존 이미지 찾기 (Id가 없으면 예외처리)
        ImageEntity updateImage = imageRepository.findById(imageId)
                .orElseThrow(() -> new S3NotFoundException("Image not found"));

        // S3에서 기존 이미지 삭제
        amazonS3.deleteObject(bucketName, updateImage.getFileKey());

        for (S3UpdateReqDto.ImageUpdate update : imageReqDto.getImages()) {

            // 새로운 이미지 업로드
            MultipartFile newFile = update.getNewFile();
            String fileName = System.currentTimeMillis() + "_" + newFile.getOriginalFilename(); // 새로운 파일 이름 생성
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(newFile.getSize());
            amazonS3.putObject(bucketName, fileName, newFile.getInputStream(), metadata);

            // 새로운 이미지 URL 생성
            String newUrl = amazonS3.getUrl(bucketName, fileName).toString();

            // 데이터베이스 업데이트
            updateImage.setUrl(newUrl);
            updateImage.setFileKey(fileName);
            imageRepository.save(updateImage);

            response.add(new S3ResDto(updateImage.getImageId(), newUrl));
        }

        return response; // 응답 리스트 반환
    }

    // S3 이미지 삭제
    public void deleteImage(String imageId) throws S3NotFoundException {
        // 유효성 검사
        S3NotFoundException.validateImageId(imageId);

        // 이미지 정보 가져오기 (Id가 없으면 예외처리)
        ImageEntity imageEntity = imageRepository.findById(imageId)
                .orElseThrow(() -> new S3NotFoundException("Image not found"));

        // S3에서 이미지 삭제
        amazonS3.deleteObject(bucketName, imageEntity.getFileKey());

        // 데이터베이스에서 이미지 삭제
        imageRepository.delete(imageEntity);

    }


}
