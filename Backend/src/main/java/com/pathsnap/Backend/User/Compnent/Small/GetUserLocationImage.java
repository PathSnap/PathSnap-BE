package com.pathsnap.Backend.User.Compnent.Small;

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
public class GetUserLocationImage {

    private final PhotoRecordRepository photoRecordRepository;
    private final ImagePhotoRepository imagePhotoRepository;


    // recordId로 이미지 리스트 가져오는 메서드
    public ImageListResDto exec(String recordId) {
        List<PhotoRecordEntity> photos = photoRecordRepository.findByRecord_RecordId(recordId);
        ImageListResDto imageListResDto = new ImageListResDto(new ArrayList<>());

        if (!photos.isEmpty()) {
            String photoRecordId = photos.get(0).getPhotoRecordId();
            List<ImagePhotoEntity> imagePhotos = imagePhotoRepository.findByPhotoRecord_PhotoRecordId(photoRecordId);

            if (!imagePhotos.isEmpty()) {
                ImagePhotoEntity firstImagePhoto = imagePhotos.get(0);
                ImageResDto imageResDto = new ImageResDto(
                        firstImagePhoto.getImage().getImageId(),
                        firstImagePhoto.getImage().getUrl()
                );
                imageListResDto.getImages().add(imageResDto);
            }
        }
        return imageListResDto;
    }
}
