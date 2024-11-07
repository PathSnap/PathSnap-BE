// PackTripCreator.java
package com.pathsnap.Backend.PackTrip.Component;

import com.pathsnap.Backend.PackTrip.Dto.Req.PackTripReqDto;
import com.pathsnap.Backend.PackTrip.Entity.PackTripEntity;
import com.pathsnap.Backend.User.Entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreatePackTrip {

    public PackTripEntity exec(UserEntity user, PackTripReqDto packTripReqDto) {
        return PackTripEntity.builder()
                .packTripId(UUID.randomUUID().toString())
                .packTripName(packTripReqDto.getPackTripName())
                .user(user)
                .tripDates(new ArrayList<>())  // 초기화
                .build();
    }
}
