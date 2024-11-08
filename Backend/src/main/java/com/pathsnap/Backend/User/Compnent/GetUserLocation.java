package com.pathsnap.Backend.User.Compnent;

import com.pathsnap.Backend.Image.Dto.Res.ImageListResDto;
import com.pathsnap.Backend.Record.Entity.Record1Entity;
import com.pathsnap.Backend.User.Compnent.Small.GetUserLocationImage;
import com.pathsnap.Backend.User.Dto.Res.LocationResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor

public class GetUserLocation {
    private final GetUserLocationImage getUserLocationImage;

    // LocationResDto.LocationDto 리스트 반환 메서드
    public List<LocationResDto.LocationDto> exec(List<Record1Entity> records) {
        List<LocationResDto.LocationDto> locationDTOs = new ArrayList<>();
        for (Record1Entity record : records) {
            ImageListResDto imageListResDto = getUserLocationImage.exec(record.getRecordId());
            LocationResDto.LocationDto locationDto = new LocationResDto.LocationDto(
                    record.getRecordId(),
                    record.getRecordName(),
                    imageListResDto
            );
            locationDTOs.add(locationDto);
        }
        return locationDTOs;
    }
}
