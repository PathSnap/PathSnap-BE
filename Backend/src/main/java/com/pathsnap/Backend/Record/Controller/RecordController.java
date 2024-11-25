package com.pathsnap.Backend.Record.Controller;

import com.pathsnap.Backend.Record.Dto.Req.RecordEditReqDto;
import com.pathsnap.Backend.Record.Dto.Req.RecordUpdateReqDto;
import com.pathsnap.Backend.Record.Dto.Res.RecordDetailResDto;
import com.pathsnap.Backend.Record.Dto.Res.RecordEditResDto;
import com.pathsnap.Backend.Record.Dto.Res.RecordStartDTO;
import com.pathsnap.Backend.Record.Dto.Res.RecordUpdateResDto;
import com.pathsnap.Backend.Record.Service.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //json 형식으로 반환
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/records")
public class RecordController implements RecordControllerDocs {

    private final CreateRecordService createRecordService;
    private final GetRecordService getRecordService;
    private final UpdateSeqRecordService updateSeqRecordService;
    private final EditRecordService editRecordService;
    private final DeleteRecordService deleteRecordService;


    @GetMapping("/start/{userId}/{recordIsGroup}")
    public ResponseEntity<RecordStartDTO> startRecord(@PathVariable String userId, @PathVariable boolean recordIsGroup) {

        return ResponseEntity.ok(createRecordService.startNewRecord(userId, recordIsGroup));
    }

    @GetMapping("/detail/{recordId}")
    public ResponseEntity<RecordDetailResDto> getRecordDetails(@PathVariable String recordId) {

        return ResponseEntity.ok(getRecordService.getRecordDetail(recordId));
    }

    @PutMapping("/update")
    public ResponseEntity<RecordUpdateResDto> updateRecordDetails(@RequestBody RecordUpdateReqDto request) {

        return ResponseEntity.ok(updateSeqRecordService.updateRecordDetails(request));
    }

    @PostMapping("/edit")
    public ResponseEntity<RecordEditResDto> editRecordName(@RequestBody RecordEditReqDto request) {

        return ResponseEntity.ok(editRecordService.editRecordName(request));
    }

    @DeleteMapping("/delete/{recordId}")
    public ResponseEntity<Void> deleteRecord(@PathVariable String recordId) {

        deleteRecordService.deleteRecord(recordId);
        return ResponseEntity.ok().build();
    }
}
