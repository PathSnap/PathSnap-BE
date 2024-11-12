package com.pathsnap.Backend.WebSocket.Dto.Res;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecordStartResDto {
    private String roomId;
    private String recordId;
}
