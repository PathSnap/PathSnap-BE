package com.pathsnap.Backend.PackTrip.Controller;

import com.pathsnap.Backend.PackTrip.Dto.Req.PackTripReqDto;
import com.pathsnap.Backend.PackTrip.Dto.Res.PackTripResDto;
import com.pathsnap.Backend.PackTrip.Service.CreatePackTripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PackTripController {

    private final CreatePackTripService packTripService;

    @PostMapping("/profiles/trips")
    public ResponseEntity<PackTripResDto> createPackTrip(
            @RequestBody PackTripReqDto packTripReqDTO) {
        return ResponseEntity.ok(packTripService.createPackTrip(packTripReqDTO.getUserId(), packTripReqDTO));
    }
}
