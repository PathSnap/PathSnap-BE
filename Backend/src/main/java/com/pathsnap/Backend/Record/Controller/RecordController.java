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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //json 형식으로 반환
@RequestMapping("/records")
public class RecordController implements RecordControllerDocs{

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

    @Operation(summary = "기록 상세 조회", description = "피라미터로 받은 기록으로 기록 관련 정보들을 전달")
    @GetMapping("/detail/{recordId}")
    public ResponseEntity<RecordDetailResDto> getRecordDetail(@PathVariable String recordId) {

        RecordDetailResDto response = recordDetailService.getRecordDetail(recordId);

        return ResponseEntity.ok(response);
    }
    @Operation(summary = "기록 순서 변경", description = "바디로 받은 순서가 변경된 포토기록과 경로기록을 저장하고 변경된 정보들 전달 ")
    @PutMapping("/update")
    public ResponseEntity<RecordUpdateResDto> updateRecordDetails(@RequestBody RecordUpdateReqDto request) {

        return ResponseEntity.ok(recordUpdateService.updateRecordDetails(request));
    }

    @Operation(summary = "기록 이름 수정", description = "바디로 받은 변경된 기록이름을 저장하고 변경된 정보들을 전달")
    @PostMapping("/edit")
    public ResponseEntity<RecordEditResDto> editRecordName(@RequestBody RecordEditReqDto request){
        return ResponseEntity.ok(recordEditService.editRecordName(request));
    }
}
