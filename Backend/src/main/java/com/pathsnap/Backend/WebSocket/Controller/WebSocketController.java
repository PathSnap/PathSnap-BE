package com.pathsnap.Backend.WebSocket.Controller;

import com.pathsnap.Backend.RouteRecord.Dto.Res.RouteRecordStartDto;
import com.pathsnap.Backend.RouteRecord.Service.CreateRouteRecordService;
import com.pathsnap.Backend.WebSocket.Dto.Res.RecordStartResDto;
import com.pathsnap.Backend.WebSocket.Service.CreateGroupRecordService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/group")
public class WebSocketController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final CreateGroupRecordService createGroupRecordService;
    private final CreateRouteRecordService createRouteRecordService;

    // 로그 객체를 선언
    private static final Logger log = LoggerFactory.getLogger(WebSocketController.class);

    @GetMapping("/records/start/{userId}/{recordIsGroup}")
    public RecordStartResDto createRoom(@PathVariable String userId, @PathVariable boolean recordIsGroup) {
        return createGroupRecordService.createRoom(userId, recordIsGroup);
    }


    @MessageMapping("/routes/start/{recordId}/{roomId}")
    public void chat(@DestinationVariable String recordId, @DestinationVariable String roomId) {
        RouteRecordStartDto response = createRouteRecordService.startRoute(recordId);

        simpMessagingTemplate.convertAndSend("/sub/routes/" + roomId, response);
        log.info("Message sent to room: {}, with recordId: {}",roomId, recordId);
    }

}