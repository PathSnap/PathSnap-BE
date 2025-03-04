package com.pathsnap.Backend.PhotoRecord.Service;

import com.pathsnap.Backend.Image.Dto.Res.ImageResDto;
import com.pathsnap.Backend.Image.Entity.Image1Entity;
import com.pathsnap.Backend.ImagePhoto.Component.CreateImagePhoto;
import com.pathsnap.Backend.ImagePhoto.Entity.ImagePhoto1Entity;
import com.pathsnap.Backend.PhotoRecord.Component.CreatePhotoRecord;
import com.pathsnap.Backend.PhotoRecord.Dto.Req.PhotoRecordCreateReqDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoRecordResDto;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecord1Entity;
import com.pathsnap.Backend.PhotoRecord.Repository.PhotoRecordRepository;
import com.pathsnap.Backend.Record.Component.CheckRecord;
import com.pathsnap.Backend.Record.Entity.Record1Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.Builder;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Builder
@RequiredArgsConstructor
public class CreatePhotoService {

    private final CheckRecord recordCheck;
    private final PhotoRecordRepository photoRecordRepository;
    private final CreatePhotoRecord createPhotoRecord;
    private final CreateImagePhoto createImagePhoto;

    public PhotoRecordResDto createPhoto(String recordId, PhotoRecordCreateReqDto request) {

        if (request.getImages() == null || request.getImages().isEmpty()) {
            throw new IllegalArgumentException("이미지 필드는 필수입니다.");
        }
        //recordId 있는지 확인
        Record1Entity record = recordCheck.exec(recordId);

        //photoId 생성
        String photoId = UUID.randomUUID().toString();

        //photoRecord 생성
        PhotoRecord1Entity photoRecord = createPhotoRecord.exec(photoId,record,request);

        //imagePhoto 목록 생성하여 photoRecord에 업데이트
        List<ImagePhoto1Entity> imagePhotos = createImagePhoto.exec(photoRecord, request.getImages());
        photoRecord.setImagePhotos(imagePhotos);

        //photoRecord 저장
        PhotoRecord1Entity createdPhotoRecord = photoRecordRepository.save(photoRecord);


        List<ImageResDto> imageUrls = createdPhotoRecord.getImagePhotos()
                .stream()
                .map(ImagePhoto -> {
                    Image1Entity image = ImagePhoto.getImage();
                    return ImageResDto.builder() // ImageReqDto로 직접 매핑
                            .imageId(image.getImageId())
                            .url(image.getUrl())
                            .build();
                })
                .collect(Collectors.toList());

        return PhotoRecordResDto.builder()
                .photoId(createdPhotoRecord.getPhotoRecordId())
                .seq(createdPhotoRecord.getSeq())
                .images(imageUrls) // 또는 필요한 데이터로 수정
                .photoTitle(createdPhotoRecord.getPhotoTitle())
                .photoLocation(createdPhotoRecord.getPhotoLocation())
                .photoContent(createdPhotoRecord.getPhotoContent())
                .photoDate(createdPhotoRecord.getPhotoDate()) // 형식 변환 필요할 수 있음
                .lat(createdPhotoRecord.getLat())
                .lng(createdPhotoRecord.getLng())
                .build();

    }
}
