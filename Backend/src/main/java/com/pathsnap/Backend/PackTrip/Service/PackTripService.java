package com.pathsnap.Backend.PackTrip.Service;

import com.pathsnap.Backend.Exception.UserNotFoundException;
import com.pathsnap.Backend.PackTrip.Dto.Req.PackTripReqDto;
import com.pathsnap.Backend.PackTrip.Dto.Res.PackTripResDto;
import com.pathsnap.Backend.PackTrip.Entity.PackTripEntity;
import com.pathsnap.Backend.PackTrip.Repository.PackTripRepository;
import com.pathsnap.Backend.TripDate.Entity.TripDateEntity;
import com.pathsnap.Backend.User.Entity.UserEntity;
import com.pathsnap.Backend.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PackTripService {

    private final UserRepository userRepository;

    private final PackTripRepository packTripRepository;

    // 달력- 여행 날짜 묶기
    public PackTripResDto createPackTrip(String userId, PackTripReqDto packTripReqDto) {
        // 사용자 존재 여부 확인
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        // PackTripEntity 생성
        PackTripEntity packTrip = PackTripEntity.builder()
                .packTripId(UUID.randomUUID().toString())
                .packTripName(packTripReqDto.getPackTripName())
                .user(user)
                .tripDates(new ArrayList<>())  // 초기화
                .build();

        // 날짜 목록 추가
        for (String dateStr : packTripReqDto.getDates()) {
            TripDateEntity tripDate = TripDateEntity.builder()
                    .tripDateId(UUID.randomUUID().toString())
                    .packTrip(packTrip)
                    .tripDate(java.sql.Date.valueOf(dateStr))
                    .build();

            packTrip.getTripDates().add(tripDate); // 저장
        }

        // 패키지 여행 저장
        packTripRepository.save(packTrip);

        // 응답 Dto 생성
        return new PackTripResDto(packTrip.getPackTripId(), packTrip.getPackTripName(), packTripReqDto.getDates());
    }


}

