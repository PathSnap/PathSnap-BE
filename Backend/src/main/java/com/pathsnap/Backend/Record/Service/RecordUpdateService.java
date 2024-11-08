package com.pathsnap.Backend.Record.Service;

import com.pathsnap.Backend.PhotoRecord.Component.CheckPhotoRecord;
import com.pathsnap.Backend.PhotoRecord.Component.UpdateSeqPhotoRecord;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoUpdateResDto;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecordEntity;
import com.pathsnap.Backend.PhotoRecord.Repository.PhotoRecordRepository;
import com.pathsnap.Backend.Record.Dto.Req.RecordUpdateReqDto;
import com.pathsnap.Backend.Record.Dto.Res.RecordUpdateResDto;
import com.pathsnap.Backend.RouteRecord.Component.CheckRouteRecord;
import com.pathsnap.Backend.RouteRecord.Component.UpdateSeqRouteReecord;
import com.pathsnap.Backend.RouteRecord.Dto.Res.RouteUpdateResDto;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecordEntity;
import com.pathsnap.Backend.RouteRecord.Repository.RouteRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordUpdateService {

    private final UpdateSeqPhotoRecord updateSeqPhotoRecord;
    private final UpdateSeqRouteReecord updateSeqRouteReecord;


    public RecordUpdateResDto updateRecordDetails(RecordUpdateReqDto request){

        // 사진기록 순서 업데이트 및 응답 생성
        List<PhotoUpdateResDto> photoResponses = updateSeqPhotoRecord.exec(request.getUpdatePhotos());
        // 경로기록 순서 업데이트 및 응답 생성
        List<RouteUpdateResDto> routeResponses = updateSeqRouteReecord.exec(request.getUpdatedRoutes());

            // 필요에 따라 결과 반환
            return RecordUpdateResDto.builder()
                    .recordId(request.getRecordId())
                    .updatePhotos(photoResponses)
                    .updatedRoutes(routeResponses)
                    .build();
        }
    }
