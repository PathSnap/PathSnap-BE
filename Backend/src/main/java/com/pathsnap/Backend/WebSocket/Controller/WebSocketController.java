package com.pathsnap.Backend.WebSocket.Controller;

import com.pathsnap.Backend.RouteRecord.Dto.Res.RouteRecordStartDto;
import com.pathsnap.Backend.RouteRecord.Service.CreateRouteRecordService;
import com.pathsnap.Backend.WebSocket.WebSocketService.WebSocketAuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
public class WebSocketController {
    private final WebSocketAuthService webSocketAuthService;
    private final CreateRouteRecordService createRouteRecordService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private static final Logger log = LoggerFactory.getLogger(WebSocketController.class);

    // route 생성
    @MessageMapping("/routes/start/{userId}/{recordId}/{seq}")
    public void CreateRoute(@DestinationVariable String userId, @DestinationVariable String recordId, @DestinationVariable Integer seq) {

        // userId 확인
        webSocketAuthService.webSocketUser(userId, recordId);

        // route 생성
        RouteRecordStartDto response = createRouteRecordService.startRoute(recordId,seq);

        simpMessagingTemplate.convertAndSend("/sub/routes/" + recordId, response);
        log.info("Message sent to routeId: {}", response.getRouteId());
    }


}