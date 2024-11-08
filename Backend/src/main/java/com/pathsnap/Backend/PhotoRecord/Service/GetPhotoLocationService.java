package com.pathsnap.Backend.PhotoRecord.Service;

import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoDataResDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoLocationResDto;
import com.pathsnap.Backend.Record.Entity.RecordEntity;
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

    public List<PhotoLocationResDto> getPhotosWithinRadius(String userId, double lon, double lat, double radius) {
        List<PhotoLocationResDto> photoLocations = new ArrayList<>();

        List<String> recordIds = recordRepository.findByUser(userCheck.exec(userId))
                .stream()
                .map(RecordEntity::getRecordId)
                .toList();

        recordIds.forEach(recordId -> {
            RecordEntity record = recordRepository.findById(recordId).orElse(null);
            if (record != null) {
                List<PhotoDataResDto> photos = new ArrayList<>(); // 특정 기록에 대한 사진 리스트 초기화

                record.getPhotoRecords().stream()
                        .filter(photoRecord -> isWithinRadius(photoRecord.getLat(), photoRecord.getLng(), lat, lon, radius))
                        .forEach(photoRecord ->{
                            photoRecord.getImagePhotos().forEach(imagePhoto -> {
                                PhotoDataResDto photoDataResDto = new PhotoDataResDto(
                                        photoRecord.getPhotoRecordId(),
                                        imagePhoto.getImage().getUrl(),
                                        photoRecord.getLat(),
                                        photoRecord.getLng()
                                );
                                photos.add(photoDataResDto);
                            });
                        });
                if (!photos.isEmpty()) { // 사진이 존재할 경우에만 추가
                    photoLocations.add(new PhotoLocationResDto(recordId, photos));
                }
            }
        });
        return photoLocations;
    }
    // 반경 계산 메서드 (예시)
    private boolean isWithinRadius( double photoLat, double photoLng, double Lat, double Lon, double radius){

        // 반경 계산 로직 작성 (거리 계산 공식) // 위도와 경도를 라디안으로 변환
        double earthRadius = 6371e3; // 지구 반경 (미터 단위)
        double dLat = Math.toRadians(Lat - photoLat);
        double dLon = Math.toRadians(Lon - photoLng);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(photoLat)) * Math.cos(Math.toRadians(Lat)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = earthRadius * c; // 두 좌표 간 거리 계산

        // 계산된 거리가 반경 내에 있는지 확인
        return distance <= radius;
    }
}
