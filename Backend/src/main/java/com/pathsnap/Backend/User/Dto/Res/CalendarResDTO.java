package com.pathsnap.Backend.User.Dto.Res;

import com.pathsnap.Backend.Image.Dto.Res.ImageListResDTO;
import com.pathsnap.Backend.PackTrip.Dto.Res.PackTripResDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CalendarResDTO {
    private List<CalendarDTO> calendar;
    private List<PackTripResDTO> newTrips;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class CalendarDTO {
        private String recordId;
        private Date startDate;
        private String recordName;
        private ImageListResDTO images;
    }
}
