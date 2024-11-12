package com.pathsnap.Backend.WebSocket.Service;

import com.pathsnap.Backend.Record.Component.CreateRecord;
import com.pathsnap.Backend.Record.Entity.Record1Entity;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import com.pathsnap.Backend.User.Compnent.UserCheck;
import com.pathsnap.Backend.User.Entity.User1Entity;
import com.pathsnap.Backend.WebSocket.Dto.Res.RecordStartResDto;
import com.pathsnap.Backend.WebSocket.Entity.Room;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateGroupRecordService {
    private final RecordRepository recordRepository;
    private final CreateRecord createRecord;
    private final UserCheck userCheck;

    private final Map<String, Room> chatRooms = new LinkedHashMap<>();


//    private final ObjectMapper objectMapper;


//    //id로 방을 찾고 결과로 ChatRoom 객체 반환
//    public Room findRoomById(String roomId) {
//        return chatRooms.get(roomId);
//    }

    //방 생성
    public RecordStartResDto createRoom(String userId, boolean recordIsGroup) {

        //userId가 있는지 확인
        User1Entity user = userCheck.exec(userId);

        //record 생성
        Record1Entity record = createRecord.exec(user,recordIsGroup);
        recordRepository.save(record);

        String randomId = UUID.randomUUID().toString();

        //랜덤 roomId 생성
        Room chatRoom = Room.builder()
                .roomId(randomId)
                .build();

        chatRooms.put(randomId, chatRoom); //방 목록에 추가

        return new RecordStartResDto(randomId,record.getRecordId());
    }

    // 메시지 관련 주석처리

//    public <T> void sendMessage(WebSocketSession session, T message) {
//        try{
//            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        }
//    }
}