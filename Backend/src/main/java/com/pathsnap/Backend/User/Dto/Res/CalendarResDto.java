package com.pathsnap.Backend.User.Dto.Res;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pathsnap.Backend.Image.Dto.Res.ImageResDto;
import com.pathsnap.Backend.PackTrip.Dto.Res.PackTripResDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CalendarResDto {
    private List<CalendarDto> calendar;
    private List<PackTripResDto> newTrips;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class CalendarDto {
        private String recordId;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime startDate;
        private String recordName;
        private ImageResDto image;
    }
}
