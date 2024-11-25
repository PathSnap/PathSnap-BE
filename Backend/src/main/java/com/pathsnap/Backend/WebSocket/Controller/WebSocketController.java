package com.pathsnap.Backend.WebSocket.Controller;

import com.pathsnap.Backend.PhotoRecord.Dto.Req.PhotoRecordCreateReqDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Req.PhotoRecordEditReqDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoRecordResDto;
import com.pathsnap.Backend.PhotoRecord.Service.CreatePhotoService;
import com.pathsnap.Backend.PhotoRecord.Service.DeletePhotoService;
import com.pathsnap.Backend.PhotoRecord.Service.EditPhotoService;
import com.pathsnap.Backend.RouteRecord.Dto.Req.RouteReqDto;
import com.pathsnap.Backend.RouteRecord.Dto.Res.RouteRecordStartDto;
import com.pathsnap.Backend.RouteRecord.Service.CreateRouteRecordService;
import com.pathsnap.Backend.RouteRecord.Service.EditRouteRecordService;
import com.pathsnap.Backend.WebSocket.WebSocketService.WebSocketAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
public class WebSocketController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final WebSocketAuthService webSocketAuthService;

    private final CreateRouteRecordService createRouteRecordService;
    private final EditRouteRecordService editRouteRecordService;

    private final CreatePhotoService photoRecordService;
    private final EditPhotoService editPhotoService;
    private final DeletePhotoService deletePhotoService;

    // record 저장
    @Transactional
    @MessageMapping("/records/save/{userId}/{recordId}")
    public void SaveRecord(@DestinationVariable String userId, @DestinationVariable String recordId) {
        // userId 확인
        webSocketAuthService.webSocketUser(userId, recordId);

        simpMessagingTemplate.convertAndSend("/sub/records/" + recordId, "closed");
    }

    // route 생성
    @MessageMapping("/routes/start/{userId}/{recordId}/{seq}")
    public void CreateRoute(@DestinationVariable String userId, @DestinationVariable String recordId, @DestinationVariable Integer seq) {
        // userId 확인
        webSocketAuthService.webSocketUser(userId, recordId);

        // route 생성
        RouteRecordStartDto response = createRouteRecordService.startRoute(recordId,seq);

        simpMessagingTemplate.convertAndSend("/sub/routes/" + recordId, response);
    }

    // route 정보 저장
    @Transactional
    @MessageMapping("/routes/save/{userId}/{recordId}")
    public void SaveRoute(RouteReqDto routeReqDto , @DestinationVariable String userId, @DestinationVariable String recordId) {
        // userId 확인
        webSocketAuthService.webSocketUser(userId, recordId);

        //route 저장
        editRouteRecordService.editRoute(routeReqDto);

        simpMessagingTemplate.convertAndSend("/sub/routes/" + recordId, "200 OK");
    }

    // photo 데이터 저장
    @MessageMapping("/photos/create/{recordId}")
    public void CreatePhoto(PhotoRecordCreateReqDto request, @DestinationVariable String recordId) {
        //photo 저장
        PhotoRecordResDto response = photoRecordService.createPhoto(recordId, request);

        simpMessagingTemplate.convertAndSend("/sub/photos/" + recordId, response);
    }

    // photo 데이터 수정
    @Transactional
    @MessageMapping("/photos/edit/{recordId}")
    public void EditPhoto(PhotoRecordEditReqDto request, @DestinationVariable String recordId) {
        //recordId 존재하는지 확인
        webSocketAuthService.getRecord(recordId);

        //photo 수정
        PhotoRecordResDto response = editPhotoService.editPhoto(request);

        simpMessagingTemplate.convertAndSend("/sub/photos/" + recordId, response);
    }

    // photo 데이터 삭제
    @MessageMapping("/photos/delete/{photoId}/{recordId}")
    public void EditPhoto( @DestinationVariable String photoId, @DestinationVariable String recordId) {
        //recordId 존재하는지 확인
        webSocketAuthService.getRecord(recordId);

        //photo 삭제
        deletePhotoService.deletePhoto(photoId);

        simpMessagingTemplate.convertAndSend("/sub/photos/" + recordId, "200 ok");
    }


}