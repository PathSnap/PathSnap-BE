package com.pathsnap.Backend.PackTrip.Service;

import com.pathsnap.Backend.PackTrip.Component.PackTripCreate;
import com.pathsnap.Backend.PackTrip.Component.TripDateUpdate;
import com.pathsnap.Backend.PackTrip.Dto.Req.PackTripReqDto;
import com.pathsnap.Backend.PackTrip.Dto.Res.PackTripResDto;
import com.pathsnap.Backend.PackTrip.Entity.PackTripEntity;
import com.pathsnap.Backend.PackTrip.Repository.PackTripRepository;
import com.pathsnap.Backend.User.Compnent.UserCheck;
import com.pathsnap.Backend.User.Entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PackTripCreateService {

    private final UserCheck userCheck;
    private final PackTripCreate packTripCreate;
    private final TripDateUpdate tripDateUpdate;
    private final PackTripRepository packTripRepository;

    // 달력- 여행 날짜 묶기
    public PackTripResDto createPackTrip(String userId, PackTripReqDto packTripReqDto) {
        // 사용자 존재 여부 확인
        UserEntity user = userCheck.exec(userId);
        // PackTripEntity 생성
        PackTripEntity packTrip = packTripCreate.exec(user, packTripReqDto);
        // TripDate 날짜 목록 추가
        tripDateUpdate.exec(packTrip, packTripReqDto.getDates());
        // 패키지 여행 저장
        packTripRepository.save(packTrip);

        // 응답 Dto 생성
        return new PackTripResDto(packTrip.getPackTripId(), packTrip.getPackTripName(), packTripReqDto.getDates());
    }


}

