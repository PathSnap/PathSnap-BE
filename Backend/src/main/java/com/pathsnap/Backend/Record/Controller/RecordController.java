package com.pathsnap.Backend.Record.Controller;

import com.pathsnap.Backend.Record.Dto.Req.RecordEditReqDto;
import com.pathsnap.Backend.Record.Dto.Req.RecordUpdateReqDto;
import com.pathsnap.Backend.Record.Dto.Res.RecordDetailResDto;
import com.pathsnap.Backend.Record.Dto.Res.RecordEditResDto;
import com.pathsnap.Backend.Record.Dto.Res.RecordStartDTO;
import com.pathsnap.Backend.Record.Dto.Res.RecordUpdateResDto;
import com.pathsnap.Backend.Record.Service.RecordDetailService;
import com.pathsnap.Backend.Record.Service.RecordEditService;
import com.pathsnap.Backend.Record.Service.RecordStartService;
import com.pathsnap.Backend.Record.Service.RecordUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //json 형식으로 반환
@RequestMapping("/records")
public class RecordController {

    @Autowired
    RecordStartService recordStartService;
    @Autowired
    RecordDetailService recordDetailService;
    @Autowired
    RecordUpdateService recordUpdateService;
    @Autowired
    RecordEditService recordEditService;

    @GetMapping("/start/{userId}/{recordIsGroup}")
    public ResponseEntity<RecordStartDTO> startRecord(@PathVariable String userId, @PathVariable boolean recordIsGroup) {
        String recordId = recordStartService.startNewRecord(userId, recordIsGroup);

        RecordStartDTO response = RecordStartDTO.builder()
                .recordId(recordId)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/detail/{recordId}")
    public ResponseEntity<RecordDetailResDto> getRecordDetail(@PathVariable String recordId) {

        RecordDetailResDto response = recordDetailService.getRecordDetail(recordId);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<RecordUpdateResDto> updateRecordDetails(@RequestBody RecordUpdateReqDto request) {

        return ResponseEntity.ok(recordUpdateService.updateRecordDetails(request));
    }

    @PostMapping("/edit")
    public ResponseEntity<RecordEditResDto> editRecordName(@RequestBody RecordEditReqDto request){
        return ResponseEntity.ok(recordEditService.editRecordName(request));
    }
}