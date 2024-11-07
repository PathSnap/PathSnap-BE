package com.pathsnap.Backend.PhotoRecord.Service.PhotoRecordService;

import com.pathsnap.Backend.Exception.ImageNotFoundException;
import com.pathsnap.Backend.Exception.PhotoRecordNotFoundException;
import com.pathsnap.Backend.Image.Entity.ImageEntity;
import com.pathsnap.Backend.ImagePhoto.Entity.ImagePhotoEntity;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoSummaryResDto;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecordEntity;
import com.pathsnap.Backend.PhotoRecord.Repository.PhotoRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class SummaryPhotoService {


    private final PhotoRecordRepository photoRecordRepository;

    public PhotoSummaryResDto getSummaryPhoto(String photoId){
        PhotoRecordEntity photoRecordSummary = photoRecordRepository.findById(photoId)
                .orElseThrow(()->new PhotoRecordNotFoundException(photoId));

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
