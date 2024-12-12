package com.pathsnap.Backend.PackTrip.Service;

import com.pathsnap.Backend.PackTrip.Entity.PackTrip1Entity;
import com.pathsnap.Backend.PackTrip.Repository.PackTripRepository;
import com.pathsnap.Backend.User.Compnent.CheckUser;
import com.pathsnap.Backend.User.Entity.User1Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePackTripService {

    private final CheckUser userCheck;
    private final PackTripRepository packTripRepository;

    public void deletePackTrip(String userId, String packTripId) {
        // 사용자 존재 여부 확인
        userCheck.exec(userId);

        // PackTrip 엔티티 찾기
        PackTrip1Entity packTrip = packTripRepository.findById(packTripId)
                .orElseThrow(() -> new RuntimeException("PackTrip not found"));

        // 해당 PackTrip 삭제
        packTripRepository.delete(packTrip);
    }
}
