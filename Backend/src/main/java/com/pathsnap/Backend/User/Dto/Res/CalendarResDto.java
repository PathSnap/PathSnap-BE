package com.pathsnap.Backend.User.Dto.Res;

import com.pathsnap.Backend.Image.Dto.Res.ImageResDto;
import com.pathsnap.Backend.PackTrip.Dto.Res.PackTripResDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
        private Date startDate;
        private String recordName;
        private ImageResDto image;
    }
}
