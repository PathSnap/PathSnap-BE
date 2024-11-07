package com.pathsnap.Backend.PackTrip.Controller;

import com.pathsnap.Backend.PackTrip.Dto.Req.PackTripReqDto;
import com.pathsnap.Backend.PackTrip.Dto.Res.PackTripResDto;
import com.pathsnap.Backend.PackTrip.Service.PackTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles/trips")
public class PackTripController {

    @Autowired
    private PackTripService packTripService;

    @PostMapping("/{userId}")
    public PackTripResDto createPackTrip(
            @PathVariable String userId,
            @RequestBody PackTripReqDto packTripReqDTO) {
        return packTripService.createPackTrip(userId, packTripReqDTO);
    }
}
