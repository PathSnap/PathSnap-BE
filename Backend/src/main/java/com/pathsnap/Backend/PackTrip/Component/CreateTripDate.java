// TripDateAdder.java
package com.pathsnap.Backend.PackTrip.Component;

import com.pathsnap.Backend.PackTrip.Entity.PackTrip1Entity;
import com.pathsnap.Backend.TripDate.Entity.TripDate1Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateTripDate {

    public void exec(PackTrip1Entity packTrip, List<String> dates) {
        for (String dateStr : dates) {
            TripDate1Entity tripDate = TripDate1Entity.builder()
                    .tripDateId(UUID.randomUUID().toString())
                    .packTrip(packTrip)
                    .tripDate(Date.valueOf(dateStr))
                    .build();

            packTrip.getTripDates().add(tripDate); // 날짜 추가
        }
    }
}
