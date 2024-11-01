package com.pathsnap.Backend.PhotoRecord.Controller;

import com.pathsnap.Backend.PhotoRecord.Dto.Req.PhotoRecordReqDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoRecordResDto;
import com.pathsnap.Backend.PhotoRecord.Service.PhotoRecordService.CreatePhotoService;
import com.pathsnap.Backend.PhotoRecord.Service.PhotoRecordService.UpdatePhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/photos")
public class PhotoRecordController {

    @Autowired
    @Qualifier("createPhotoService") // 주입할 빈 이름과 일치
    private CreatePhotoService photoRecordService;

    @Autowired
    @Qualifier("updatePhotoService")
    private UpdatePhotoService updatePhotoService;

    @PostMapping("/create/{recordId}")
    public ResponseEntity<PhotoRecordResDto> addPhoto(@PathVariable String recordId,
                                                      @RequestBody PhotoRecordReqDto request) {
        PhotoRecordResDto response = photoRecordService.createPhoto(recordId, request);
        return ResponseEntity.ok(response);

    }

    @PutMapping("/edit")
    public ResponseEntity<PhotoRecordResDto> updatePhoto(@RequestBody PhotoRecordReqDto request){

        PhotoRecordResDto response = updatePhotoService.updatePhoto(request);
        return ResponseEntity.ok(response);

    }

}
