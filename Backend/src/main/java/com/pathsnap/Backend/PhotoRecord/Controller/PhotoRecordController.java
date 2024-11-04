package com.pathsnap.Backend.PhotoRecord.Controller;

import com.pathsnap.Backend.PhotoRecord.Dto.Req.PhotoRecordReqDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoLocationResDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoRecordResDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoSummaryResDto;
import com.pathsnap.Backend.PhotoRecord.Service.PhotoRecordService.CreatePhotoService;
import com.pathsnap.Backend.PhotoRecord.Service.PhotoRecordService.GetPhotoLocationService;
import com.pathsnap.Backend.PhotoRecord.Service.PhotoRecordService.SummaryPhotoService;
import com.pathsnap.Backend.PhotoRecord.Service.PhotoRecordService.DeletePhotoService;
import com.pathsnap.Backend.PhotoRecord.Service.PhotoRecordService.UpdatePhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/photos")
public class PhotoRecordController {

    @Autowired
    @Qualifier("createPhotoService") // 주입할 빈 이름과 일치
    private CreatePhotoService photoRecordService;

    @Autowired
    @Qualifier("updatePhotoService")
    private UpdatePhotoService updatePhotoService;

    @Autowired
    @Qualifier("getPhotoLocationService")
    private GetPhotoLocationService getLocationService;
    
    @Autowired
    @Qualifier("summaryPhotoService")
    private SummaryPhotoService summaryPhotoService;
  
    @Autowired
    @Qualifier("deletePhotoService")
    private DeletePhotoService deletePhotoService;

    @PostMapping("/create/{recordId}")
    public ResponseEntity<PhotoRecordResDto> addPhoto(@PathVariable String recordId,
                                                      @RequestBody PhotoRecordReqDto request) {
        PhotoRecordResDto response = photoRecordService.createPhoto(recordId, request);
        return ResponseEntity.ok(response);

    }

    @PutMapping("/edit")
    public ResponseEntity<PhotoRecordResDto> updatePhoto(@RequestBody PhotoRecordReqDto request) {

        PhotoRecordResDto response = updatePhotoService.updatePhoto(request);
        return ResponseEntity.ok(response);

    }
  
    @GetMapping("/summary/{photoId}")
    public ResponseEntity<PhotoSummaryResDto> getSummaryPhoto(@PathVariable String photoId){

        PhotoSummaryResDto response = summaryPhotoService.getSummaryPhoto(photoId);
        return ResponseEntity.ok(response);
    
    }
  
    @GetMapping("/{userId}/{lon}/{lat}/{radius}")
    public ResponseEntity<List<PhotoLocationResDto>> getPhotosWithinRadius(
            @PathVariable String userId,
            @PathVariable double lon,
            @PathVariable double lat,
            @PathVariable double radius) {
        List<PhotoLocationResDto> response = getLocationService.getPhotosWithinRadius(userId, lon, lat, radius);
        return ResponseEntity.ok(response);

    }
  
    @DeleteMapping("/delete/{photoId}")
    public ResponseEntity<Void> deletePhoto(@PathVariable String photoId){

        deletePhotoService.deletePhoto(photoId);
        return ResponseEntity.ok().build();

    }
}
