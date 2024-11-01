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
import lombok.Builder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Builder
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

        String photoId = UUID.randomUUID().toString();

        PhotoRecordEntity photoRecord = PhotoRecordEntity.builder()
                .photoRecordId(photoId)
                .record(record)
                .photoTitle(request.getPhotoTitle())
                .seq(request.getSeq())
                .photoContent(request.getPhotoContent())
                .lng(request.getLng())
                .lat(request.getLat())
                .photoDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        List<ImagePhotoEntity> imagePhotos = new ArrayList<>();
        for (ImageReqDto imageReqDto : request.getImages()) {

            ImageEntity image = imageRepository.findById(imageReqDto.getImageId())
                    .orElseThrow(() -> new ImageNotFoundException(imageReqDto.getImageId()));

            ImagePhotoEntity imagePhoto = ImagePhotoEntity.builder()
                    .imagePhotoId(UUID.randomUUID().toString())
                    .image(image)
                    .photoRecord(photoRecord)
                    .build();

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
