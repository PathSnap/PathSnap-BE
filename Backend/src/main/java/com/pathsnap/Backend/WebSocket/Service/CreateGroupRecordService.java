package com.pathsnap.Backend.WebSocket.Service;

import com.pathsnap.Backend.Record.Component.CreateRecord;
import com.pathsnap.Backend.Record.Dto.Res.RecordStartDTO;
import com.pathsnap.Backend.Record.Entity.Record1Entity;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import com.pathsnap.Backend.User.Compnent.UserCheck;
import com.pathsnap.Backend.User.Entity.User1Entity;
import com.pathsnap.Backend.WebSocket.Dto.Res.GroupRecordStartResDto;
import com.pathsnap.Backend.WebSocket.Dto.Res.RoomRedisResDto;
import com.pathsnap.Backend.WebSocket.Repository.RoomRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateGroupRecordService {

    private final RecordRepository recordRepository;
    private final RoomRedisRepository roomRedisRepository;
    private final CreateRecord createRecord;
    private final UserCheck userCheck;


    public GroupRecordStartResDto startNewRecord(String userId, boolean recordIsGroup) {

        //userId가 있는지 확인
        User1Entity user = userCheck.exec(userId);

        //record 생성
        Record1Entity record = createRecord.exec(user,recordIsGroup);
        recordRepository.save(record);

        // roomId 생성
        String roomId = UUID.randomUUID().toString();
        LocalDateTime createdAt = LocalDateTime.now();

        //roomId Redis에 저장
        RoomRedisResDto roomRedisResDto = new RoomRedisResDto(roomId, createdAt);
        roomRedisRepository.save(roomRedisResDto);


        return GroupRecordStartResDto.builder()
                .recordId(record.getRecordId())
                .roomId(roomId)
                .build();
    }
}
