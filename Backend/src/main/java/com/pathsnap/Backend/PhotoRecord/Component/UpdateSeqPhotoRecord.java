package com.pathsnap.Backend.PhotoRecord.Component;

import com.pathsnap.Backend.PhotoRecord.Dto.Req.PhotoUpdateReqDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoUpdateResDto;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecordEntity;
import com.pathsnap.Backend.PhotoRecord.Repository.PhotoRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UpdateSeqPhotoRecord {

    private final CheckPhotoRecord checkPhotoRecord;
    private final PhotoRecordRepository photoRecordRepository;


    public List<PhotoUpdateResDto> exec(List<PhotoUpdateReqDto> updatePhotos) {
        return updatePhotos.stream()
                .map(photoUpdate -> {
                    PhotoRecordEntity photoRecord = checkPhotoRecord.exec(photoUpdate.getPhotoId());
                    photoRecord.setSeq(photoUpdate.getNewSeq());
                    photoRecordRepository.save(photoRecord);

                    return PhotoUpdateResDto.builder()
                            .photoId(photoRecord.getPhotoRecordId())
                            .newSeq(photoRecord.getSeq())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
