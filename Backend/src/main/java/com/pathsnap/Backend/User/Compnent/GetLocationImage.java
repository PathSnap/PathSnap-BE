package com.pathsnap.Backend.Image.Component;

import com.pathsnap.Backend.Image.Dto.Res.ImageListResDto;
import com.pathsnap.Backend.Image.Dto.Res.ImageResDto;
import com.pathsnap.Backend.ImagePhoto.Entity.ImagePhotoEntity;
import com.pathsnap.Backend.ImagePhoto.Repository.ImagePhotoRepository;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecordEntity;
import com.pathsnap.Backend.PhotoRecord.Repository.PhotoRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetLocationImage {

    private final PhotoRecordRepository photoRecordRepository;
    private final ImagePhotoRepository imagePhotoRepository;


    // recordId로 사진 찾기
    public ImageListResDto exec(String recordId) {
        List<PhotoRecordEntity> photos = photoRecordRepository.findByRecord_RecordId(recordId);
        ImageListResDto imageListResDto = new ImageListResDto(new ArrayList<>());

        if (!photos.isEmpty()) {
            // PhotoRecord의 ID 가져오기
            String photoRecordId = photos.get(0).getPhotoRecordId();

            // 해당 photoRecordId로 이미지 정보 가져오기
            List<ImagePhotoEntity> imagePhotos = imagePhotoRepository.findByPhotoRecord_PhotoRecordId(photoRecordId);

            if (!imagePhotos.isEmpty()) {
                // 첫 번째 이미지만 추가
                ImagePhotoEntity firstImagePhoto = imagePhotos.get(0);
                ImageResDto imageResDto = new ImageResDto(firstImagePhoto.getImage().getImageId(), firstImagePhoto.getImage().getUrl());
                imageListResDto.getImages().add(imageResDto);
            }
        }

        return imageListResDto;
    }
}
