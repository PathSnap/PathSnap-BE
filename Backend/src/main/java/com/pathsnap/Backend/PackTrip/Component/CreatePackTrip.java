// PackTripCreator.java
package com.pathsnap.Backend.PackTrip.Component;

import com.pathsnap.Backend.PackTrip.Dto.Req.PackTripReqDto;
import com.pathsnap.Backend.PackTrip.Entity.PackTrip1Entity;
import com.pathsnap.Backend.User.Entity.User1Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreatePackTrip {

    public PackTrip1Entity exec(User1Entity user, PackTripReqDto packTripReqDto) {
        return PackTrip1Entity.builder()
                .packTripId(UUID.randomUUID().toString())
                .packTripName(packTripReqDto.getPackTripName())
                .user(user)
                .tripDates(new ArrayList<>())  // 초기화
                .build();
    }
}
