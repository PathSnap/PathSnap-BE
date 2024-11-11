package com.pathsnap.Backend.WebSocket.Dto.Res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RedisHash(value = "room")
public class SaveRedisRoomDto {

    @Id
    private String roomId;
}
