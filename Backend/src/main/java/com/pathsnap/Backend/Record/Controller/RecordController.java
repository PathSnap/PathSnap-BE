package com.pathsnap.Backend.Record.Controller;

import com.pathsnap.Backend.Record.Dto.Res.RecordDetailResDto;
import com.pathsnap.Backend.Record.Dto.Res.RecordStartDTO;
import com.pathsnap.Backend.Record.Service.RecordDetailService;
import com.pathsnap.Backend.Record.Service.RecordStartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //json 형식으로 반환
@RequestMapping("/records")
public class RecordController {

    @Autowired
    RecordStartService recordStartService;
    @Autowired
    RecordDetailService recordDetailService;


    @GetMapping("/start/{userId}/{recordIsGroup}")
    public ResponseEntity<RecordStartDTO> startRecord(@PathVariable String userId, @PathVariable boolean recordIsGroup) {
        String recordId = recordStartService.startNewRecord(userId,recordIsGroup);

        RecordStartDTO response = RecordStartDTO.builder()
                .recordId(recordId)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/detail/{recordId}")
    public ResponseEntity<RecordDetailResDto> getRecordDetail(@PathVariable String recordId){

        RecordDetailResDto response = recordDetailService.getRecordDetail(recordId);

        return  ResponseEntity.ok(response);
    }
}
