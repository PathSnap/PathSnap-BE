package com.pathsnap.Backend.User.Compnent;

import com.pathsnap.Backend.Image.Dto.Res.ImageResDto;
import com.pathsnap.Backend.Record.Entity.Record1Entity;
import com.pathsnap.Backend.User.Compnent.Small.GetUserMainImage;
import com.pathsnap.Backend.User.Dto.Res.CalendarResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetCalendar {
    private final GetUserMainImage getUserMainImage;

    // CalendarResDto.CalendarDto 리스트 반환 메서드
    public List<CalendarResDto.CalendarDto> exec(List<Record1Entity> records) {
        List<CalendarResDto.CalendarDto> calendarDtos = new ArrayList<>();
        for (Record1Entity record : records) {
            ImageResDto ImageResDto = getUserMainImage.exec(record.getRecordId());
            CalendarResDto.CalendarDto calendarDto = new CalendarResDto.CalendarDto(
                    record.getRecordId(),
                    record.getStartDate(),
                    record.getRecordName(),
                    ImageResDto
            );
            calendarDtos.add(calendarDto);
        }
        return calendarDtos;
    }
}
