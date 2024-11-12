package com.pathsnap.Backend.WebSocket.Config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketHandler extends TextWebSocketHandler {

//    private final ObjectMapper objectMapper;
//    //payload를 ChatMessage 객체로 만들어 주기 위한 objectMapper
//
//    private final CreateGroupRecordService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        // 현재는 메시지 전송 처리가 필요하지 않음
//        String payload = message.getPayload(); //메세지를 가져오기
//        log.info("{}", payload); //log 출력
//
//        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
//        //payload를 ChatMessage 객체로 만들어주기
//
//        Room chatRoom = chatService.findRoomById(chatMessage.getRoomId());
//        //ChatMessage 객체에서 roomId를 가져와 일치하는 room 주입
//
//        chatRoom.handlerActions(session, chatMessage, chatService);
    }
}