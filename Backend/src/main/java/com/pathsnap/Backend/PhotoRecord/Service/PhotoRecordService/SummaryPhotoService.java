package com.pathsnap.Backend.PhotoRecord.Service.PhotoRecordService;

import com.pathsnap.Backend.Exception.ImageNotFoundException;
import com.pathsnap.Backend.Image.Entity.ImageEntity;
import com.pathsnap.Backend.ImagePhoto.Entity.ImagePhotoEntity;
import com.pathsnap.Backend.PhotoRecord.Component.CheckPhotoRecord;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoSummaryResDto;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecordEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class SummaryPhotoService {

    private final CheckPhotoRecord photoRecordCheck;

    public PhotoSummaryResDto getSummaryPhoto(String photoId){
        PhotoRecordEntity photoRecordSummary = photoRecordCheck.exec(photoId);

        ImagePhotoEntity firstImage = photoRecordSummary.getImagePhotos().stream()
                .min(Comparator.comparing(ImagePhotoEntity::getImagePhotoId))
                .orElseThrow(()->new ImageNotFoundException(photoId));

        //ImageUrl 가져오기
        ImageEntity imageEntity = firstImage.getImage();
        String Url = imageEntity.getUrl();

        return PhotoSummaryResDto.builder()
                .photoId(photoRecordSummary.getPhotoRecordId())
                .imagePhotoId(firstImage.getImagePhotoId())
                .url(Url)
                .photoTitle(photoRecordSummary.getPhotoTitle())
                .photoContent(photoRecordSummary.getPhotoContent())
                .photoDate(photoRecordSummary.getPhotoDate())
                .lat(photoRecordSummary.getLat())
                .lng(photoRecordSummary.getLng())
                .build();
    }
}
