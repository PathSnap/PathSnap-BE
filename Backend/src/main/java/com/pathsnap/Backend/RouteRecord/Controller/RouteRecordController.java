package com.pathsnap.Backend.RouteRecord.Controller;

import com.pathsnap.Backend.RouteRecord.Dto.Req.RouteReqDto;
import com.pathsnap.Backend.RouteRecord.Dto.Res.RouteRecordStartDto;
import com.pathsnap.Backend.RouteRecord.Service.RouteRecordSaveService;
import com.pathsnap.Backend.RouteRecord.Service.RouteRecordStartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/routes")
public class RouteRecordController {

    @Autowired
    RouteRecordStartService routeRecordStartService;
    @Autowired
    RouteRecordSaveService routeRecordService;

    @GetMapping("/start/{recordId}")
    public ResponseEntity<RouteRecordStartDto> RouteRecordStart(@PathVariable String recordId){

        return ResponseEntity.ok(routeRecordStartService.startRoute(recordId));

    }

    @PostMapping("/save")
    public ResponseEntity<RouteRecordStartDto> saveRoute(@RequestBody RouteReqDto routeReqDto){

        routeRecordService.saveRoute(routeReqDto);

        return ResponseEntity.ok().build();
    }
}
