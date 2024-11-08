package com.pathsnap.Backend.RouteRecord.Controller;

import com.pathsnap.Backend.RouteRecord.Dto.Req.RouteReqDto;
import com.pathsnap.Backend.RouteRecord.Dto.Res.RouteRecordStartDto;
import com.pathsnap.Backend.RouteRecord.Service.EditRouteRecordService;
import com.pathsnap.Backend.RouteRecord.Service.CreateRouteRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/routes")
public class RouteRecordController {

    private final CreateRouteRecordService createRouteRecordService;
    private final EditRouteRecordService editRouteRecordService;

    @GetMapping("/start/{recordId}")
    public ResponseEntity<RouteRecordStartDto> RouteRecordStart(@PathVariable String recordId){

        return ResponseEntity.ok(createRouteRecordService.startRoute(recordId));

    }

    @PostMapping("/save")
    public ResponseEntity<RouteRecordStartDto> editRoute(@RequestBody RouteReqDto routeReqDto){

        editRouteRecordService.editRoute(routeReqDto);

        return ResponseEntity.ok().build();
    }
}
