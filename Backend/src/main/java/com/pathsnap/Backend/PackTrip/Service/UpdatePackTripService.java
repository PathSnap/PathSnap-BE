package com.pathsnap.Backend.PackTrip.Service;

import com.pathsnap.Backend.PackTrip.Component.CreateTripDate;
import com.pathsnap.Backend.PackTrip.Dto.Req.UpdatePackTripReqDto;
import com.pathsnap.Backend.PackTrip.Dto.Res.PackTripResDto;
import com.pathsnap.Backend.PackTrip.Entity.PackTrip1Entity;
import com.pathsnap.Backend.PackTrip.Repository.PackTripRepository;
import com.pathsnap.Backend.TripDate.Repository.TripDateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UpdatePackTripService {

    private final CreateTripDate tripDateUpdate;
    private final PackTripRepository packTripRepository;
    private final TripDateRepository tripDateRepository;

    // 달력- 여행 날짜 묶기
    public PackTripResDto updatePackTrip(UpdatePackTripReqDto updatePackTripReqDto) {
        // PackTrip 엔티티 찾기
        PackTrip1Entity packTrip = packTripRepository.findById(updatePackTripReqDto.getPackTripId())
                .orElseThrow(() -> new RuntimeException("PackTrip not found"));

        // 패키지 여행 이름 업데이트
        packTrip.setPackTripName(updatePackTripReqDto.getPackTripName());

        // 기존 날짜 목록 삭제
        tripDateRepository.deleteAllInBatch(packTrip.getTripDates());

        packTrip.getTripDates().clear(); // 기존 날짜 목록 삭제
        tripDateUpdate.exec(packTrip, updatePackTripReqDto.getDates()); // 새로운 날짜 추가

        // 업데이트된 여행 패키지 저장
        packTripRepository.save(packTrip);

        // 응답 DTO 생성
        return new PackTripResDto(packTrip.getPackTripId(), packTrip.getPackTripName(), updatePackTripReqDto.getDates());
    }


}

