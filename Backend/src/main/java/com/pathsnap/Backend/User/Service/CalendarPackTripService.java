package com.pathsnap.Backend.User.Service;

import com.pathsnap.Backend.PackTrip.Dto.Res.PackTripResDto;
import com.pathsnap.Backend.PackTrip.Entity.PackTripEntity;
import com.pathsnap.Backend.PackTrip.Repository.PackTripRepository;
import com.pathsnap.Backend.TripDate.Entity.TripDateEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalendarPackTripService {

    private final PackTripRepository packTripRepository;

    // 달력 불러오기
    public List<PackTripResDto> getPackTrips(String userId, Integer month) {

        // userId로 packTrip 불러오기
        List<PackTripEntity> packTrips = packTripRepository.findByUser_UserId(userId);

        // 월 날짜 필터링
        return packTrips.stream()
                .map(packTrip -> {
                    List<String> filteredDates = packTrip.getTripDates().stream()
                            .map(TripDateEntity::getTripDate) // 각 tripDate를 가져옴
                            .filter(tripDate -> { // yyyy-MM-dd 형식변환, 문자열로 변환
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                String dateString = sdf.format(tripDate);
                                // 월 추출, 비교
                                int tripMonth = Integer.parseInt(dateString.split("-")[1]);
                                return tripMonth == month;
                            })
                            .map(tripDate -> {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                return sdf.format(tripDate);
                            })
                            .collect(Collectors.toList());

                    return new PackTripResDto(
                            packTrip.getPackTripId(),
                            packTrip.getPackTripName(),
                            filteredDates
                    );
                })
                .filter(packTripResDto -> !packTripResDto.getDates().isEmpty()) // 날짜가 있는 경우만 필터링
                .collect(Collectors.toList());
    }
}
