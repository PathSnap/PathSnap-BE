package com.pathsnap.Backend.User.Service;

import com.pathsnap.Backend.Image.Component.GetLocationImage;
import com.pathsnap.Backend.User.Compnent.CheckUser;
import com.pathsnap.Backend.Image.Dto.Res.ImageListResDto;
import com.pathsnap.Backend.User.Dto.Res.LocationResDto;
import com.pathsnap.Backend.Record.Entity.RecordEntity;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetLocationService {

    private final RecordRepository recordRepository;
    private final CheckUser userCheck;
    private final GetLocationImage getLocationImage;

    // 여행 이미지 불러오기
    public LocationResDto getLocations(String userId) {
        // 사용자 존재 여부 확인
        userCheck.exec(userId);

        // Date기준으로 오름차순 정렬
        List<RecordEntity> records = recordRepository.findByUser_UserIdOrderByStartDateAsc(userId);

        List<LocationResDto.LocationDto> locationDTOs = new ArrayList<>();

        // recordId로 사진 찾기
        for (RecordEntity record : records) {
            ImageListResDto imageListResDto = getLocationImage.exec(record.getRecordId());

            // LocationDTO에 추가
            locationDTOs.add(new LocationResDto.LocationDto(record.getRecordId(), record.getRecordName(), imageListResDto));
        }

        return new LocationResDto(locationDTOs);
    }
}
