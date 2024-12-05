package com.pathsnap.Backend.PhotoRecord.Service;

import com.pathsnap.Backend.Image.Dto.Res.ImageResDto;
import com.pathsnap.Backend.Image.Entity.Image1Entity;
import com.pathsnap.Backend.ImagePhoto.Component.EditImagePhoto;
import com.pathsnap.Backend.ImagePhoto.Entity.ImagePhoto1Entity;
import com.pathsnap.Backend.PhotoRecord.Component.CheckPhotoRecord;
import com.pathsnap.Backend.PhotoRecord.Component.EditPhotoRecord;
import com.pathsnap.Backend.PhotoRecord.Dto.Req.PhotoRecordEditReqDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoRecordResDto;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecord1Entity;
import com.pathsnap.Backend.PhotoRecord.Repository.PhotoRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EditPhotoService {

    private final PhotoRecordRepository photoRecordRepository;
    private final CheckPhotoRecord photoRecordCheck;
    private final EditPhotoRecord editPhotoRecord;
    private final EditImagePhoto editImagePhoto;

    public PhotoRecordResDto editPhoto(PhotoRecordEditReqDto request) {

        // photoId 있는지 확인
        PhotoRecord1Entity photoRecordEdit = photoRecordCheck.exec(request.getPhotoId());

        // photoRecord 수정
        photoRecordEdit = editPhotoRecord.exec(photoRecordEdit, request);

        // Edited imagePhoto 목록들을 photoRecord에 업데이트
        List<ImagePhoto1Entity> updatedImagePhotos = editImagePhoto.exec(photoRecordEdit, request.getImages());
        photoRecordEdit.setImagePhotos(updatedImagePhotos);

        // Edited photoRecord 저장
        PhotoRecord1Entity updatedPhotoRecord = photoRecordRepository.save(photoRecordEdit);

        List<ImageResDto> imageUrls = updatedPhotoRecord.getImagePhotos()
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
                .photoId(updatedPhotoRecord.getPhotoRecordId())
                .seq(updatedPhotoRecord.getSeq())
                .images(imageUrls)
                .photoTitle(updatedPhotoRecord.getPhotoTitle())
                .photoLocation(updatedPhotoRecord.getPhotoLocation())
                .photoContent(updatedPhotoRecord.getPhotoContent())
                .photoDate(updatedPhotoRecord.getPhotoDate())
                .lat(updatedPhotoRecord.getLat())
                .lng(updatedPhotoRecord.getLng())
                .build();
    }
}
