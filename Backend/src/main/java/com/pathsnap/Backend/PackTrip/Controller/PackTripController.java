package com.pathsnap.Backend.PackTrip.Controller;

import com.pathsnap.Backend.PackTrip.Dto.Req.PackTripReqDto;
import com.pathsnap.Backend.PackTrip.Dto.Req.UpdatePackTripReqDto;
import com.pathsnap.Backend.PackTrip.Dto.Res.PackTripResDto;
import com.pathsnap.Backend.PackTrip.Service.CreatePackTripService;
import com.pathsnap.Backend.PackTrip.Service.DeletePackTripService;
import com.pathsnap.Backend.PackTrip.Service.UpdatePackTripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/trips")
@RequiredArgsConstructor
public class PackTripController implements PackTripControllerDocs{

    private final CreatePackTripService createPackTripService;
    private final UpdatePackTripService updatePackTripService;
    private final DeletePackTripService deletePackTripService;


    @PostMapping
    public ResponseEntity<PackTripResDto> createPackTrip(
            @RequestBody PackTripReqDto packTripReqDTO) {
        return ResponseEntity.ok(createPackTripService.createPackTrip(packTripReqDTO.getUserId(), packTripReqDTO));
    }

    @PutMapping
    public ResponseEntity<PackTripResDto> updatePackTrip(
            @RequestBody UpdatePackTripReqDto updatePackTripReqDto) {
        return ResponseEntity.ok(updatePackTripService.updatePackTrip(updatePackTripReqDto));
    }

    @DeleteMapping("/{packTripId}")
    public ResponseEntity<Void> deletePackTrip(@PathVariable String packTripId) {
        deletePackTripService.deletePackTrip(packTripId);
        return ResponseEntity.ok().build();
    }
}
