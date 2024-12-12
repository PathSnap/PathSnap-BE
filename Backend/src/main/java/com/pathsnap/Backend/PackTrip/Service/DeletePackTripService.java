package com.pathsnap.Backend.PackTrip.Service;

import com.pathsnap.Backend.PackTrip.Entity.PackTrip1Entity;
import com.pathsnap.Backend.PackTrip.Repository.PackTripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePackTripService {

    private final PackTripRepository packTripRepository;

    public void deletePackTrip(String packTripId) {

        // PackTrip 엔티티 찾기
        PackTrip1Entity packTrip = packTripRepository.findById(packTripId)
                .orElseThrow(() -> new RuntimeException("PackTrip not found"));

        // 해당 PackTrip 삭제
        packTripRepository.delete(packTrip);
    }
}
