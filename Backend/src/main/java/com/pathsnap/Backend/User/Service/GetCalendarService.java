package com.pathsnap.Backend.User.Service;

import com.pathsnap.Backend.PackTrip.Dto.Res.PackTripResDto;
import com.pathsnap.Backend.User.Compnent.CheckUser;
import com.pathsnap.Backend.User.Compnent.GetCalendar;
import com.pathsnap.Backend.User.Compnent.GetCalendarPackTrip;
import com.pathsnap.Backend.User.Dto.Res.CalendarResDto;
import com.pathsnap.Backend.Record.Entity.Record1Entity;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCalendarService {

    private final CheckUser checkUser;
    private final RecordRepository recordRepository;


    private final GetCalendarPackTrip getCalenderPackTrip;
    private final GetCalendar getCalendar;

    public CalendarResDto getCalendar(String userId, Integer month) {

        // 1. record 데이터 불러오기
        // 사용자 존재 여부 확인 및 예외 처리
        checkUser.exec(userId);

        // Date기준으로 오름차순 정렬
        List<Record1Entity> records = recordRepository.findByUser_UserIdOrderByStartDateAsc(userId);


        // getCalendar에서 반복문을 사용하여 calendarDto 생성
        List<CalendarResDto.CalendarDto> calendarDtos = getCalendar.exec(records);

        // 2. PackTrip 불러오기
        List<PackTripResDto> newTrips = getCalenderPackTrip.exec(userId, month);

        // 최종 결과 반환
        return new CalendarResDto(calendarDtos, newTrips);


    }
}
