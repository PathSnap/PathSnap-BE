package com.pathsnap.Backend.PhotoRecord.Component.PhotoLocation;

import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoDataResDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoLocationResDto;
import com.pathsnap.Backend.Record.Entity.Record1Entity;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FilterPhotoLocation {
    private final RadiusCalculator radiusCalculator;
    private final RecordRepository recordRepository;

    public List<PhotoLocationResDto> exec(List<String> recordIds, double lat, double lon, double radius) {
        List<PhotoLocationResDto> photoLocations = new ArrayList<>();

        recordIds.forEach(recordId -> {
            Record1Entity record = recordRepository.findById(recordId).orElse(null);
            if (record != null) {
                List<PhotoDataResDto> photos = new ArrayList<>();

                record.getPhotoRecords().stream()
                        .filter(photoRecord -> radiusCalculator.isWithinRadius(photoRecord.getLat(), photoRecord.getLng(), lat, lon, radius))
                        .forEach(photoRecord -> {
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

                if (!photos.isEmpty()) {
                    photoLocations.add(new PhotoLocationResDto(recordId, photos));
                }
            }
        });

        return photoLocations;
    }
}
