package com.pathsnap.Backend.PackTrip.Controller;

import com.pathsnap.Backend.PackTrip.Dto.Req.PackTripReqDto;
import com.pathsnap.Backend.PackTrip.Dto.Res.PackTripResDto;
import com.pathsnap.Backend.PackTrip.Service.PackTripCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles/trips")
@RequiredArgsConstructor
public class PackTripController {

    private final PackTripCreateService packTripService;

    @PostMapping("/{userId}")
    public ResponseEntity<PackTripResDto> createPackTrip(
            @PathVariable String userId,
            @RequestBody PackTripReqDto packTripReqDTO) {
        return ResponseEntity.ok(packTripService.createPackTrip(userId, packTripReqDTO));
    }
}
