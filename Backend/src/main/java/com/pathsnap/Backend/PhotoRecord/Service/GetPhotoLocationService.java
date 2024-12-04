package com.pathsnap.Backend.PhotoRecord.Service;

import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoDataResDto;
import com.pathsnap.Backend.Record.Entity.Record1Entity;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import com.pathsnap.Backend.User.Compnent.CheckUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPhotoLocationService {

    private final RecordRepository recordRepository;
    private final CheckUser userCheck;

    public List<PhotoDataResDto> getPhotosWithinRadius(String userId, double lon, double lat, double radius) {
        List<PhotoDataResDto> photoLocations = new ArrayList<>();

        List<Record1Entity> records= recordRepository.findByUser(userCheck.exec(userId));

        records.forEach(record -> {
            record.getPhotoRecords().stream()
                .filter(photoRecord -> isWithinRadius(photoRecord.getLat(), photoRecord.getLng(), lat, lon, radius))
                .forEach(photoRecord ->{
                    photoRecord.getImagePhotos().forEach(imagePhoto -> {
                        PhotoDataResDto photoDataResDto = new PhotoDataResDto(
                                record.getRecordId(),
                                photoRecord.getPhotoRecordId(),
                                imagePhoto.getImage().getUrl(),
                                photoRecord.getLat(),
                                photoRecord.getLng()
                        );
                        photoLocations.add(photoDataResDto);
                    });
                });
        });
        return photoLocations;
    }
    // 반경 계산 메서드 (예시)
    private boolean isWithinRadius( double photoLat, double photoLng, double Lat, double Lon, double radius){
        return (Lat-photoLat) * (Lat-photoLat) + (Lon-photoLng) * (Lon-photoLng) <= radius * radius ;
    }
}
