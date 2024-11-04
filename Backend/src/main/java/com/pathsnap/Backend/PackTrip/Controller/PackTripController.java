package com.pathsnap.Backend.PackTrip.Controller;

import com.pathsnap.Backend.PackTrip.Dto.Req.PackTripReqDTO;
import com.pathsnap.Backend.PackTrip.Dto.Res.PackTripResDTO;
import com.pathsnap.Backend.PackTrip.Service.PackTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles/trips")
public class PackTripController {

    @Autowired
    private PackTripService packTripService;

    @PostMapping("/{userId}")
    public PackTripResDTO createPackTrip(
            @PathVariable String userId,
            @RequestBody PackTripReqDTO packTripReqDTO) {
        return packTripService.createPackTrip(userId, packTripReqDTO);
    }
}
