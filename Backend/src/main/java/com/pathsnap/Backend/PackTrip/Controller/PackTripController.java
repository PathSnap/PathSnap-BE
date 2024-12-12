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
@RequestMapping("/profiles/trips")
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

    @PutMapping("/{userId}/{packTripId}")
    public ResponseEntity<PackTripResDto> updatePackTrip(
            @PathVariable String userId,
            @PathVariable String packTripId,
            @RequestBody UpdatePackTripReqDto updatePackTripReqDto) {
        return ResponseEntity.ok(updatePackTripService.updatePackTrip(userId, packTripId, updatePackTripReqDto));
    }

    @DeleteMapping("/{userId}/{packTripId}")
    public ResponseEntity<Void> deletePackTrip(@PathVariable String userId, @PathVariable String packTripId) {

        deletePackTripService.deletePackTrip(userId, packTripId);
        return ResponseEntity.ok().build();
    }
}
