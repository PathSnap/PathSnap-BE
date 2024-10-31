package com.pathsnap.Backend.RouteRecord.Controller;

import com.pathsnap.Backend.RouteRecord.Dto.Res.RouteRecordStartDTO;
import com.pathsnap.Backend.RouteRecord.Service.RouteRecordStartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/routes")
public class RouteRecordStartController {

    @Autowired
    RouteRecordStartService routeRecordStartService;

    @GetMapping("/start/{recordId}")
    public ResponseEntity<RouteRecordStartDTO> RouteRecordStart(@PathVariable String recordId){
        String routeId = routeRecordStartService.startRoute(recordId);

        RouteRecordStartDTO response = RouteRecordStartDTO.builder()
                .routeId(routeId)
                .build();

        return ResponseEntity.ok(response);

    }
}
