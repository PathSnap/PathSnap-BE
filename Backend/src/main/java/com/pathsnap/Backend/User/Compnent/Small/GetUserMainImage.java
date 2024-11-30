package com.pathsnap.Backend.User.Compnent.Small;

import com.pathsnap.Backend.Image.Dto.Res.ImageResDto;
import com.pathsnap.Backend.ImagePhoto.Entity.ImagePhoto1Entity;
import com.pathsnap.Backend.ImagePhoto.Repository.ImagePhotoRepository;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecord1Entity;
import com.pathsnap.Backend.PhotoRecord.Repository.PhotoRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetUserMainImage {

    private final PhotoRecordRepository photoRecordRepository;
    private final ImagePhotoRepository imagePhotoRepository;


    // recordId로 대표 이미지를 가져오는 메서드
    public ImageResDto exec(String recordId) {
        // recordId로 photo_date 기준 가장 빠른 PhotoRecord1Entity를 조회
        List<PhotoRecord1Entity> photos = photoRecordRepository.findByRecordIdOrderByPhotoDateAsc(recordId);

        if (!photos.isEmpty()) {
            String photoRecordId = photos.get(0).getPhotoRecordId();
            List<ImagePhoto1Entity> imagePhotos = imagePhotoRepository.findByPhotoRecord_PhotoRecordId(photoRecordId);

            if (!imagePhotos.isEmpty()) {
                ImagePhoto1Entity firstImagePhoto = imagePhotos.get(0);
                return new ImageResDto(
                        firstImagePhoto.getImage().getImageId(),
                        firstImagePhoto.getImage().getUrl()
                );
            }
        }
        return null;
    }
}
