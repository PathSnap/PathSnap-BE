package com.pathsnap.Backend.WebSocket.Dto.Res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class Room {
    private String roomId;

}