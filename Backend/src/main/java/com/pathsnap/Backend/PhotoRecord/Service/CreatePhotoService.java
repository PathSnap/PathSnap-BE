package com.pathsnap.Backend.PhotoRecord.Service;

import com.pathsnap.Backend.ImagePhoto.Component.CreateImagePhoto;
import com.pathsnap.Backend.ImagePhoto.Entity.ImagePhotoEntity;
import com.pathsnap.Backend.PhotoRecord.Component.CreatePhotoRecord;
import com.pathsnap.Backend.PhotoRecord.Dto.Req.PhotoRecordReqDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoRecordResDto;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecordEntity;
import com.pathsnap.Backend.PhotoRecord.Repository.PhotoRecordRepository;
import com.pathsnap.Backend.Record.Component.CheckRecord;
import com.pathsnap.Backend.Record.Entity.RecordEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.Builder;
import java.util.List;
import java.util.UUID;

@Service
@Builder
@RequiredArgsConstructor
public class CreatePhotoService {

    private final CheckRecord recordCheck;
    private final PhotoRecordRepository photoRecordRepository;
    private final CreatePhotoRecord createPhotoRecord;
    private final CreateImagePhoto createImagePhoto;

    public PhotoRecordResDto createPhoto(String recordId, PhotoRecordReqDto request) {

        if (request.getImages() == null || request.getImages().isEmpty()) {
            throw new IllegalArgumentException("이미지 필드는 필수입니다.");
        }
        //recordId 있는지 확인
        RecordEntity record = recordCheck.exec(recordId);

        //photoId 생성
        String photoId = UUID.randomUUID().toString();

        //photoRecord 생성
        PhotoRecordEntity photoRecord = createPhotoRecord.exec(photoId,record,request);

        //imagePhoto 목록 생성하여 photoRecord에 업데이트
        List<ImagePhotoEntity> imagePhotos = createImagePhoto.exec(photoRecord, request.getImages());
        photoRecord.setImagePhotos(imagePhotos);

        //photoRecord 저장
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
