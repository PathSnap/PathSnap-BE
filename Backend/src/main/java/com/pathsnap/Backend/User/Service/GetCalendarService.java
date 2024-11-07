package com.pathsnap.Backend.User.Service;

import com.pathsnap.Backend.Image.Component.GetLocationImage;
import com.pathsnap.Backend.Image.Dto.Res.ImageListResDto;
import com.pathsnap.Backend.ImagePhoto.Repository.ImagePhotoRepository;
import com.pathsnap.Backend.PackTrip.Dto.Res.PackTripResDto;
import com.pathsnap.Backend.PhotoRecord.Repository.PhotoRecordRepository;
import com.pathsnap.Backend.User.Compnent.CheckUser;
import com.pathsnap.Backend.User.Compnent.GetCalenderPackTrip;
import com.pathsnap.Backend.User.Dto.Res.CalendarResDto;
import com.pathsnap.Backend.Record.Entity.RecordEntity;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCalendarService {

    private final CheckUser checkUser;
    private final RecordRepository recordRepository;

    private final PhotoRecordRepository photoRecordRepository;

    private final ImagePhotoRepository imagePhotoRepository;

    private final GetCalenderPackTrip getCalenderPackTrip;
    private final GetLocationImage getLocationImage;

    public CalendarResDto getCalendar(String userId, Integer month) {

        // 1. record 데이터 불러오기
        // 사용자 존재 여부 확인 및 예외 처리
        checkUser.exec(userId);

        // Date기준으로 오름차순 정렬
        List<RecordEntity> records = recordRepository.findByUser_UserIdOrderByStartDateAsc(userId);

        List<CalendarResDto.CalendarDto> calendarDtos = new ArrayList<>();

        // recordId로 사진 찾기
        for (RecordEntity record : records) {
            ImageListResDto imageListResDto = getLocationImage.exec(record.getRecordId());

            // LocationDTO에 추가
            calendarDtos.add(new CalendarResDto.CalendarDto(record.getRecordId(), record.getStartDate(), record.getRecordName(), imageListResDto));
        }

        // 2. PackTrip 불러오기
        List<PackTripResDto> newTrips = getCalenderPackTrip.exec(userId, month);

        // 최종 결과 반환
        return new CalendarResDto(calendarDtos, newTrips);


    }
}
