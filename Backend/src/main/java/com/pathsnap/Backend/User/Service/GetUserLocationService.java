package com.pathsnap.Backend.User.Service;

import com.pathsnap.Backend.User.Compnent.CheckUser;
import com.pathsnap.Backend.User.Compnent.GetUserLocation;
import com.pathsnap.Backend.User.Dto.Res.LocationResDto;
import com.pathsnap.Backend.Record.Entity.RecordEntity;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUserLocationService {

    private final RecordRepository recordRepository;
    private final CheckUser CheckUser;
    private final GetUserLocation getLocation;

    // 여행 이미지 불러오기
    public LocationResDto getLocations(String userId) {
        // 사용자 존재 여부 확인
        CheckUser.exec(userId);

        // Date기준으로 오름차순 정렬
        List<RecordEntity> records = recordRepository.findByUser_UserIdOrderByStartDateAsc(userId);

        // getLocation에서 반복문을 사용하여 LocationDto 생성
        List<LocationResDto.LocationDto> locationDTOs = getLocation.exec(records);

        return new LocationResDto(locationDTOs);
    }
}
