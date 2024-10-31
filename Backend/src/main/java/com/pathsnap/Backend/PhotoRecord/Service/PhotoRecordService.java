package com.pathsnap.Backend.PhotoRecord.Service;

import com.pathsnap.Backend.Exception.ImageNotFoundException;
import com.pathsnap.Backend.Exception.RecordNotFoundException;
import com.pathsnap.Backend.Image.Dto.Req.ImageReqDto;
import com.pathsnap.Backend.Image.Entity.ImageEntity;
import com.pathsnap.Backend.Image.Repository.ImageRepository;
import com.pathsnap.Backend.ImagePhoto.Entity.ImagePhotoEntity;
import com.pathsnap.Backend.PhotoRecord.Dto.Req.PhotoRecordReqDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoRecordResDto;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecordEntity;
import com.pathsnap.Backend.PhotoRecord.Repository.PhotoRecordRepository;
import com.pathsnap.Backend.Record.Entity.RecordEntity;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PhotoRecordService {

    @Autowired
    PhotoRecordRepository photoRecordRepository;
    @Autowired
    RecordRepository recordRepository;

    @Autowired
    ImageRepository imageRepository;

    public PhotoRecordResDto createPhoto(String recordId, PhotoRecordReqDto request) {

        if (request.getImages() == null || request.getImages().isEmpty()) {
            throw new IllegalArgumentException("이미지 필드는 필수입니다.");
        }

        RecordEntity record = recordRepository.findById(recordId)
                .orElseThrow(() -> new RecordNotFoundException(recordId));

        PhotoRecordEntity photoRecord = new PhotoRecordEntity();

        String photoId = UUID.randomUUID().toString();
        photoRecord.setPhotoRecordId(photoId);
        photoRecord.setRecord(record);

        photoRecord.setPhotoTitle(request.getPhotoTitle());
        photoRecord.setSeq(request.getSeq());
        photoRecord.setPhotoContent(request.getPhotoContent());
        photoRecord.setLng(request.getLng());
        photoRecord.setLat(request.getLat());

        Date date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        photoRecord.setPhotoDate(date);

        List<ImagePhotoEntity> imagePhotos = new ArrayList<>();
        for (ImageReqDto imageReqDto : request.getImages()) {

            ImagePhotoEntity imagePhoto = new ImagePhotoEntity();
            imagePhoto.setImagePhotoId(UUID.randomUUID().toString());

            ImageEntity image = imageRepository.findById(imageReqDto.getImageId())
                    .orElseThrow(() -> new ImageNotFoundException(imageReqDto.getImageId()));

            imagePhoto.setImage(image);
            imagePhoto.setPhotoRecord(photoRecord);
            imagePhotos.add(imagePhoto);
        }

        photoRecord.setImagePhotos(imagePhotos);
        PhotoRecordEntity createdPhotoRecord = photoRecordRepository.save(photoRecord);

        return PhotoRecordResDto.builder()
                .photoId(createdPhotoRecord.getPhotoRecordId())
                .seq(createdPhotoRecord.getSeq())
                .images(request.getImages()) // 또는 필요한 데이터로 수정
                .photoTitle(createdPhotoRecord.getPhotoTitle())
                .photoContent(createdPhotoRecord.getPhotoContent())
                .photoDate(createdPhotoRecord.getPhotoDate().toString()) // 형식 변환 필요할 수 있음
                .lat(createdPhotoRecord.getLat())
                .lng(createdPhotoRecord.getLng())
                .build();

    }
}
