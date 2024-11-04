package com.pathsnap.Backend.PhotoRecord.Service.PhotoRecordService;

import com.pathsnap.Backend.Exception.ImageNotFoundException;
import com.pathsnap.Backend.Exception.PhotoRecordNotFoundException;
import com.pathsnap.Backend.Image.Dto.Req.ImageReqDto;
import com.pathsnap.Backend.Image.Entity.ImageEntity;
import com.pathsnap.Backend.Image.Repository.ImageRepository;
import com.pathsnap.Backend.ImagePhoto.Entity.ImagePhotoEntity;
import com.pathsnap.Backend.PhotoRecord.Dto.Req.PhotoRecordReqDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoRecordResDto;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecordEntity;
import com.pathsnap.Backend.PhotoRecord.Repository.PhotoRecordRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("updatePhotoService")
@Builder
public class UpdatePhotoService {

    @Autowired
    private PhotoRecordRepository photoRecordRepository;
    @Autowired
    private ImageRepository imageRepository;

    public PhotoRecordResDto updatePhoto(PhotoRecordReqDto request) {

        PhotoRecordEntity photoRecordEdit = photoRecordRepository.findById(request.getPhotoId())
                .orElseThrow(() -> new PhotoRecordNotFoundException(request.getPhotoId()));

        photoRecordEdit.setPhotoRecordId(request.getPhotoId());
        photoRecordEdit.setSeq(request.getSeq());
        photoRecordEdit.setPhotoTitle(request.getPhotoTitle());
        photoRecordEdit.setPhotoContent(request.getPhotoContent());
        photoRecordEdit.setLat(request.getLat());
        photoRecordEdit.setLng(request.getLng());
        photoRecordEdit.setPhotoDate(Date.from(LocalDate.parse(request.getPhotoDate()).atStartOfDay(ZoneId.systemDefault()).toInstant())); // 문자열을 Date로 변환

        List<ImagePhotoEntity> updatedImagePhotos = new ArrayList<>();
        for(ImageReqDto imageReqDto : request.getImages()){

            ImageEntity image = imageRepository.findById(imageReqDto.getImageId())
                    .orElseThrow(() -> new ImageNotFoundException(imageReqDto.getImageId()));

            ImagePhotoEntity imagePhoto = ImagePhotoEntity.builder()
                    .imagePhotoId(UUID.randomUUID().toString())
                    .image(image)
                    .photoRecord(photoRecordEdit)
                    .build();

            updatedImagePhotos.add(imagePhoto);
        }
        photoRecordEdit.setImagePhotos(updatedImagePhotos);

        PhotoRecordEntity updatedPhotoRecord = photoRecordRepository.save(photoRecordEdit);

        return PhotoRecordResDto.builder()
                .photoId(updatedPhotoRecord.getPhotoRecordId())
                .seq(updatedPhotoRecord.getSeq())
                .images(request.getImages())
                .photoTitle(updatedPhotoRecord.getPhotoTitle())
                .photoContent(updatedPhotoRecord.getPhotoContent())
                .photoDate(updatedPhotoRecord.getPhotoDate().toString())
                .lat(updatedPhotoRecord.getLat())
                .lng(updatedPhotoRecord.getLng())
                .build();
    }
}
