package com.pathsnap.Backend.WebSocket;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter

public class Room {
    private String roomId;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder //객체 생성에서 주입하는 것에 대한 방식 - Builder Pattern
    public Room(String roomId) {
        this.roomId = roomId;
    }

    // 메세지 관련 주석처리
//    public void handlerActions(WebSocketSession session, ChatMessage chatMessage, CreateGroupRecordService chatService) {
//        if (chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
//            //방에 처음 들어왔을때
//            sessions.add(session);
//            chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
//        }
//
//        sendMessage(chatMessage, chatService);
//        //메세지 전송
//    }
//
//    private <T> void sendMessage(T message, CreateGroupRecordService chatService) {
//        sessions.parallelStream()
//                .forEach(session -> chatService.sendMessage(session, message));
//        //채팅방에 입장해 있는 모든 클라이언트에게 메세지 전송
//    }
}