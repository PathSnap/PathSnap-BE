package com.pathsnap.Backend.PhotoRecord.Component;

import com.pathsnap.Backend.Image.Dto.Req.ImageReqDto;
import com.pathsnap.Backend.Image.Entity.Image1Entity;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoRecordResDto;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecord1Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetPhotoRecord {

    private final CheckPhotoRecord photoRecordCheck;

    public List<PhotoRecordResDto> exec(String recordId) {

        // recordId로 조회한 photoRecords 목록을 가져옴
        List<PhotoRecord1Entity> photoRecords = (List<PhotoRecord1Entity>) photoRecordCheck.exec(recordId);

        // photoRecords를 PhotoRecordResDto 목록으로 변환하여 반환
        return photoRecords.stream()
                .map(photoRecord -> {
                    List<ImageReqDto> imageUrls = photoRecord.getImagePhotos()
                            .stream()
                            .map(imagePhoto -> {
                                Image1Entity image = imagePhoto.getImage();
                                return ImageReqDto.builder()
                                        .imageId(image.getImageId())
                                        .url(image.getUrl())
                                        .build();
                            })
                            .collect(Collectors.toList());

                    return PhotoRecordResDto.builder()
                            .photoId(photoRecord.getPhotoRecordId())
                            .seq(photoRecord.getSeq())
                            .images(imageUrls)
                            .photoTitle(photoRecord.getPhotoTitle())
                            .photoContent(photoRecord.getPhotoContent())
                            .photoDate(photoRecord.getPhotoDate().toString())
                            .lat(photoRecord.getLat())
                            .lng(photoRecord.getLng())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
