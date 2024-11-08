package com.pathsnap.Backend.ImagePhoto.Component;

import com.pathsnap.Backend.Exception.ImageNotFoundException;
import com.pathsnap.Backend.Image.Dto.Req.ImageReqDto;
import com.pathsnap.Backend.Image.Entity.ImageEntity;
import com.pathsnap.Backend.Image.Repository.ImageRepository;
import com.pathsnap.Backend.ImagePhoto.Entity.ImagePhotoEntity;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecordEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateImagePhoto {
    private final ImageRepository imageRepository;

    public List<ImagePhotoEntity> exec(PhotoRecordEntity photoRecord, List<ImageReqDto> imageReqDtos) {
        List<ImagePhotoEntity> imagePhotos = new ArrayList<>();

        for (ImageReqDto imageReqDto : imageReqDtos) {
            ImageEntity image = imageRepository.findById(imageReqDto.getImageId())
                    .orElseThrow(() -> new ImageNotFoundException(imageReqDto.getImageId()));

            ImagePhotoEntity imagePhoto = ImagePhotoEntity.builder()
                    .imagePhotoId(UUID.randomUUID().toString())
                    .image(image)
                    .photoRecord(photoRecord)
                    .build();

            imagePhotos.add(imagePhoto);
        }

        return imagePhotos;
    }
}
