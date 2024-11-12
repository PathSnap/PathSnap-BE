package com.pathsnap.Backend.WebSocket.Controller;

import com.pathsnap.Backend.WebSocket.Dto.Res.RecordStartResDto;
import com.pathsnap.Backend.WebSocket.Service.CreateGroupRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class GroupRecordController {
    private final CreateGroupRecordService createGroupRecordService;

    @GetMapping("group/records/start/{userId}/{recordIsGroup}")
    public RecordStartResDto createRoom(@PathVariable String userId, @PathVariable boolean recordIsGroup) {
        return createGroupRecordService.createRoom(userId, recordIsGroup);
    }

}