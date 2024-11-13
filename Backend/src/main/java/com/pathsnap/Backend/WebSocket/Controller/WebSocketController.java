package com.pathsnap.Backend.WebSocket.Controller;

import com.pathsnap.Backend.RouteRecord.Dto.Req.RouteReqDto;
import com.pathsnap.Backend.RouteRecord.Dto.Res.RouteRecordStartDto;
import com.pathsnap.Backend.RouteRecord.Service.CreateRouteRecordService;
import com.pathsnap.Backend.RouteRecord.Service.EditRouteRecordService;
import com.pathsnap.Backend.WebSocket.Dto.Res.WebSocketRecordResDto;
import com.pathsnap.Backend.WebSocket.Service.WebSocketService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/group")
public class WebSocketController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final WebSocketService createGroupRecordService;
    private final CreateRouteRecordService createRouteRecordService;
    private final EditRouteRecordService editRouteRecordService;

    // 로그 객체를 선언
    private static final Logger log = LoggerFactory.getLogger(WebSocketController.class);

    // record, room 생성
    @GetMapping("/records/start/{userId}/{recordIsGroup}")
    public WebSocketRecordResDto createRoom(@PathVariable String userId, @PathVariable boolean recordIsGroup) {
        return createGroupRecordService.createRoom(userId, recordIsGroup);
    }

    // route 생성
    @MessageMapping("/routes/start/{recordId}/{roomId}")
    public void CreateRoute(@DestinationVariable String recordId, @DestinationVariable String roomId) {
        RouteRecordStartDto response = createRouteRecordService.startRoute(recordId);

        simpMessagingTemplate.convertAndSend("/sub/routes/" + roomId, response);
        log.info("Message sent to room: {}, with recordId: {}",roomId, recordId);
    }

    // route 정보 저장
    @Transactional
    @MessageMapping("/routes/save/{roomId}")
    public void routeSave(RouteReqDto routeReqDto , @DestinationVariable String roomId) {
        editRouteRecordService.editRoute(routeReqDto);

        simpMessagingTemplate.convertAndSend("/sub/routes/" + roomId, "200 OK");
        log.info("Message sent to room: {}", roomId);

    }

}