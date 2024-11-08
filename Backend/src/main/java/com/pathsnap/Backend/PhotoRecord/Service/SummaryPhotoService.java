package com.pathsnap.Backend.PhotoRecord.Service;

import com.pathsnap.Backend.Exception.ImageNotFoundException;
import com.pathsnap.Backend.Image.Entity.Image1Entity;
import com.pathsnap.Backend.ImagePhoto.Entity.ImagePhoto1Entity;
import com.pathsnap.Backend.PhotoRecord.Component.CheckPhotoRecord;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoSummaryResDto;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecord1Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class SummaryPhotoService {

    private final CheckPhotoRecord photoRecordCheck;

    public PhotoSummaryResDto getSummaryPhoto(String photoId){
        PhotoRecord1Entity photoRecordSummary = photoRecordCheck.exec(photoId);

        ImagePhoto1Entity firstImage = photoRecordSummary.getImagePhotos().stream()
                .min(Comparator.comparing(ImagePhoto1Entity::getImagePhotoId))
                .orElseThrow(()->new ImageNotFoundException(photoId));

        //ImageUrl 가져오기
        Image1Entity imageEntity = firstImage.getImage();
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
