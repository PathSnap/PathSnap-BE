package com.pathsnap.Backend.PhotoRecord.Service.PhotoRecordService;

import com.pathsnap.Backend.Exception.ImageNotFoundException;
import com.pathsnap.Backend.Exception.PhotoRecordNotFoundException;
import com.pathsnap.Backend.Image.Entity.ImageEntity;
import com.pathsnap.Backend.Image.Repository.ImageRepository;
import com.pathsnap.Backend.ImagePhoto.Entity.ImagePhotoEntity;
import com.pathsnap.Backend.PhotoRecord.Dto.Req.PhotoRecordReqDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoRecordResDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoSummaryResDto;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecordEntity;
import com.pathsnap.Backend.PhotoRecord.Repository.PhotoRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class SummaryPhotoService {

    @Autowired
    private PhotoRecordRepository photoRecordRepository;

    public PhotoSummaryResDto getSummaryPhoto(String photoId){
        PhotoRecordEntity photoRecordSummary = photoRecordRepository.findById(photoId)
                .orElseThrow(()->new PhotoRecordNotFoundException(photoId));

        ImagePhotoEntity firstImage = photoRecordSummary.getImagePhotos().stream()
                .min(Comparator.comparing(ImagePhotoEntity::getImagePhotoId))
                .orElseThrow(()->new ImageNotFoundException(photoId));

        return PhotoSummaryResDto.builder()
                .photoId(photoRecordSummary.getPhotoRecordId())
                .imagePhotoId(firstImage.getImagePhotoId())
                .photoTitle(photoRecordSummary.getPhotoTitle())
                .photoContent(photoRecordSummary.getPhotoContent())
                .photoDate(photoRecordSummary.getPhotoDate())
                .lat(photoRecordSummary.getLat())
                .lng(photoRecordSummary.getLng())
                .build();
    }
}
