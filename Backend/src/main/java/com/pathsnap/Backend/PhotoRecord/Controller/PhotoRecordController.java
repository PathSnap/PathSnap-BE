package com.pathsnap.Backend.PhotoRecord.Controller;

import com.pathsnap.Backend.PhotoRecord.Dto.Req.PhotoRecordReqDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoRecordResDto;
import com.pathsnap.Backend.PhotoRecord.Service.PhotoRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/photos")
public class PhotoRecordController {

    @Autowired
    private PhotoRecordService photoRecordService;

    @PostMapping("/{recordId}")
    public ResponseEntity<PhotoRecordResDto> addPhoto(@PathVariable String recordId,
                                                      @RequestBody PhotoRecordReqDto request) {
        PhotoRecordResDto response = photoRecordService.createPhoto(recordId, request);
        return ResponseEntity.ok(response);

    }

}
