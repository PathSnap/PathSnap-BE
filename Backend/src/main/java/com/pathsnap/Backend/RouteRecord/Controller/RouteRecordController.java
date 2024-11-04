package com.pathsnap.Backend.RouteRecord.Controller;

import com.pathsnap.Backend.RouteRecord.Dto.Req.RouteReqDto;
import com.pathsnap.Backend.RouteRecord.Dto.Res.RouteRecordStartDTO;
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
    public ResponseEntity<RouteRecordStartDTO> RouteRecordStart(@PathVariable String recordId){
        String routeId = routeRecordStartService.startRoute(recordId);

        RouteRecordStartDTO response = RouteRecordStartDTO.builder()
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
