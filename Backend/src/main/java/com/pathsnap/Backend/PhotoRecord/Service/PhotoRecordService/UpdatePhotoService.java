package com.pathsnap.Backend.PhotoRecord.Service.PhotoRecordService;

import com.pathsnap.Backend.Exception.ImageNotFoundException;
import com.pathsnap.Backend.Image.Dto.Req.ImageReqDto;
import com.pathsnap.Backend.Image.Entity.ImageEntity;
import com.pathsnap.Backend.Image.Repository.ImageRepository;
import com.pathsnap.Backend.ImagePhoto.Entity.ImagePhotoEntity;
import com.pathsnap.Backend.PhotoRecord.Component.CheckPhotoRecord;
import com.pathsnap.Backend.PhotoRecord.Dto.Req.PhotoRecordReqDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoRecordResDto;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecordEntity;
import com.pathsnap.Backend.PhotoRecord.Repository.PhotoRecordRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service("updatePhotoService")
@Builder
@RequiredArgsConstructor
public class UpdatePhotoService {

    private final PhotoRecordRepository photoRecordRepository;
    private final ImageRepository imageRepository;
    private CheckPhotoRecord photoRecordCheck;

    public PhotoRecordResDto updatePhoto(PhotoRecordReqDto request) {

        PhotoRecordEntity photoRecordEdit = photoRecordCheck.exec(request.getPhotoId());

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

            Optional<ImagePhotoEntity> existingImagePhoto = photoRecordEdit.getImagePhotos().stream()
                    .filter(imagePhoto ->imagePhoto.getImage().getImageId().equals(imageReqDto.getImageId()))
                    .findFirst();

            ImagePhotoEntity imagePhoto;

            if (existingImagePhoto.isPresent()) {
                // 기존 이미지일 경우 기존의 ImagePhotoEntity 사용
                imagePhoto = existingImagePhoto.get();
            } else {
                // // 새로운 이미지일 경우 새로운 ImagePhotoEntity 생성
                imagePhoto = ImagePhotoEntity.builder()
                        .imagePhotoId(UUID.randomUUID().toString()) // 기존 이미지까지 새로운 PhotoId를 또 다시 발급받음
                        .image(image)
                        .photoRecord(photoRecordEdit)
                        .build();
            }
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
