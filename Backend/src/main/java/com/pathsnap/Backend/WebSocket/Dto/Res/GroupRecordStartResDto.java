package com.pathsnap.Backend.WebSocket.Dto.Res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
public class GroupRecordStartResDto {
    private String recordId;
    private String roomId;
}
