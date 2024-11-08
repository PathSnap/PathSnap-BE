package com.pathsnap.Backend.PhotoRecord.Controller;

import com.pathsnap.Backend.PhotoRecord.Dto.Req.PhotoRecordReqDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoLocationResDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoRecordResDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoSummaryResDto;
import com.pathsnap.Backend.PhotoRecord.Service.CreatePhotoService;
import com.pathsnap.Backend.PhotoRecord.Service.GetPhotoLocationService;
import com.pathsnap.Backend.PhotoRecord.Service.SummaryPhotoService;
import com.pathsnap.Backend.PhotoRecord.Service.DeletePhotoService;
import com.pathsnap.Backend.PhotoRecord.Service.EditPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/photos")
public class PhotoRecordController {

    private final CreatePhotoService photoRecordService;

    private final EditPhotoService editPhotoService;

    private final GetPhotoLocationService getLocationService;

    private final SummaryPhotoService summaryPhotoService;

    private final DeletePhotoService deletePhotoService;

    @PostMapping("/create/{recordId}")
    public ResponseEntity<PhotoRecordResDto> addPhoto(@PathVariable String recordId,
                                                      @RequestBody PhotoRecordReqDto request) {
        return ResponseEntity.ok(photoRecordService.createPhoto(recordId, request));
    }

    @PutMapping("/edit")
    public ResponseEntity<PhotoRecordResDto> editPhoto(@RequestBody PhotoRecordReqDto request) {
        return ResponseEntity.ok(editPhotoService.editPhoto(request));
    }
  
    @GetMapping("/summary/{photoId}")
    public ResponseEntity<PhotoSummaryResDto> getSummaryPhoto(@PathVariable String photoId){
        return ResponseEntity.ok(summaryPhotoService.getSummaryPhoto(photoId));
    }
  
    @GetMapping("/{userId}/{lon}/{lat}/{radius}")
    public ResponseEntity<List<PhotoLocationResDto>> getPhotosWithinRadius(
            @PathVariable String userId,
            @PathVariable double lon,
            @PathVariable double lat,
            @PathVariable double radius) {
        return ResponseEntity.ok(getLocationService.getPhotosWithinRadius(userId, lon, lat, radius));
    }
  
    @DeleteMapping("/delete/{photoId}")
    public ResponseEntity<Void> deletePhoto(@PathVariable String photoId){

        deletePhotoService.deletePhoto(photoId);
        return ResponseEntity.ok().build();

    }
}
