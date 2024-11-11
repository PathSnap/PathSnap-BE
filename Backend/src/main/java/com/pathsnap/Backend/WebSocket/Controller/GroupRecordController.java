package com.pathsnap.Backend.WebSocket.Controller;

import com.pathsnap.Backend.Record.Dto.Res.RecordStartDTO;
import com.pathsnap.Backend.WebSocket.Dto.Res.GroupRecordStartResDto;
import com.pathsnap.Backend.WebSocket.Service.CreateGroupRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class GroupRecordController {
    private final CreateGroupRecordService createGroupRecordService;

    @GetMapping("group/records/start/{userId}/{recordIsGroup}")
    public ResponseEntity<GroupRecordStartResDto> startRecord(@PathVariable String userId, @PathVariable boolean recordIsGroup) {

        return ResponseEntity.ok(createGroupRecordService.startNewRecord(userId, recordIsGroup));
    }
}
