// TripDateAdder.java
package com.pathsnap.Backend.PackTrip.Component;

import com.pathsnap.Backend.PackTrip.Entity.PackTripEntity;
import com.pathsnap.Backend.TripDate.Entity.TripDateEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TripDateUpdate {

    public void exec(PackTripEntity packTrip, List<String> dates) {
        for (String dateStr : dates) {
            TripDateEntity tripDate = TripDateEntity.builder()
                    .tripDateId(UUID.randomUUID().toString())
                    .packTrip(packTrip)
                    .tripDate(Date.valueOf(dateStr))
                    .build();

            packTrip.getTripDates().add(tripDate); // 날짜 추가
        }
    }
}
