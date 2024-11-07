package com.pathsnap.Backend.RouteRecord.Controller;

import com.pathsnap.Backend.RouteRecord.Dto.Req.RouteReqDto;
import com.pathsnap.Backend.RouteRecord.Dto.Res.RouteRecordStartDto;
import com.pathsnap.Backend.RouteRecord.Service.RouteRecordSaveService;
import com.pathsnap.Backend.RouteRecord.Service.RouteRecordStartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/routes")
public class RouteRecordController {

    private final RouteRecordStartService routeRecordStartService;
    private final RouteRecordSaveService routeRecordService;

    @GetMapping("/start/{recordId}")
    public ResponseEntity<RouteRecordStartDto> RouteRecordStart(@PathVariable String recordId){
        String routeId = routeRecordStartService.startRoute(recordId);

        RouteRecordStartDto response = RouteRecordStartDto.builder()
                .routeId(routeId)
                .build();

        return ResponseEntity.ok(response);

    }

    @PostMapping("/save")
    public ResponseEntity<String> saveRoute(@RequestBody RouteReqDto routeReqDto){

        routeRecordService.saveRoute(routeReqDto);

        return ResponseEntity.ok().build();
    }
}
