package com.pathsnap.Backend.WebSocket.Controller;

import com.pathsnap.Backend.PhotoRecord.Dto.Req.PhotoRecordCreateReqDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoRecordResDto;
import com.pathsnap.Backend.PhotoRecord.Service.CreatePhotoService;
import com.pathsnap.Backend.RouteRecord.Dto.Req.RouteReqDto;
import com.pathsnap.Backend.RouteRecord.Dto.Res.RouteRecordStartDto;
import com.pathsnap.Backend.RouteRecord.Service.CreateRouteRecordService;
import com.pathsnap.Backend.RouteRecord.Service.EditRouteRecordService;
import com.pathsnap.Backend.WebSocket.WebSocketService.WebSocketAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
public class WebSocketController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    private final WebSocketAuthService webSocketAuthService;
    private final CreateRouteRecordService createRouteRecordService;
    private final EditRouteRecordService editRouteRecordService;
    private final CreatePhotoService photoRecordService;

    // route 생성
    @MessageMapping("/routes/start/{userId}/{recordId}/{seq}")
    public void CreateRoute(@DestinationVariable String userId, @DestinationVariable String recordId, @DestinationVariable Integer seq) {
        // userId 확인
        webSocketAuthService.webSocketUser(userId, recordId);

        // route 생성
        RouteRecordStartDto response = createRouteRecordService.startRoute(recordId,seq);

        simpMessagingTemplate.convertAndSend("/sub/routes/" + recordId, response);
    }

    // route 정보 저장
    @Transactional
    @MessageMapping("/routes/save/{userId}/{recordId}")
    public void SaveRoute(RouteReqDto routeReqDto , @DestinationVariable String userId, @DestinationVariable String recordId) {
        // userId 확인
        webSocketAuthService.webSocketUser(userId, recordId);

        //route 저장
        editRouteRecordService.editRoute(routeReqDto);

        simpMessagingTemplate.convertAndSend("/sub/routes/" + recordId, "200 OK");
    }

    // photo 데이터 저장
    @MessageMapping("/photos/create/{recordId}")
    public void CreatePhoto(PhotoRecordCreateReqDto request, @DestinationVariable String recordId) {
        //photo 저장
        PhotoRecordResDto response = photoRecordService.createPhoto(recordId, request);

        simpMessagingTemplate.convertAndSend("/sub/photos/" + recordId, response);
    }


}