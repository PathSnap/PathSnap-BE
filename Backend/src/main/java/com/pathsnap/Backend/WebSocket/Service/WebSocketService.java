package com.pathsnap.Backend.WebSocket.Service;

import com.pathsnap.Backend.Record.Component.CreateRecord;
import com.pathsnap.Backend.Record.Entity.Record1Entity;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import com.pathsnap.Backend.User.Compnent.UserCheck;
import com.pathsnap.Backend.User.Entity.User1Entity;
import com.pathsnap.Backend.WebSocket.Dto.Res.WebSocketRecordResDto;
import com.pathsnap.Backend.WebSocket.Dto.Res.Room;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class WebSocketService {
    private final RecordRepository recordRepository;
    private final CreateRecord createRecord;
    private final UserCheck userCheck;

    //방 생성
    public WebSocketRecordResDto createRoom(String userId, boolean recordIsGroup) {

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

        return new WebSocketRecordResDto(chatRoom.getRoomId(),record.getRecordId());
    }

}