package com.pathsnap.Backend.Record.Service;

import com.pathsnap.Backend.Exception.RecordNotFoundException;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoUpdateResDto;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecordEntity;
import com.pathsnap.Backend.PhotoRecord.Repository.PhotoRecordRepository;
import com.pathsnap.Backend.Record.Dto.Req.RecordUpdateReqDto;
import com.pathsnap.Backend.Record.Dto.Res.RecordUpdateResDto;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import com.pathsnap.Backend.RouteRecord.Dto.Res.RouteUpdateResDto;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecordEntity;
import com.pathsnap.Backend.RouteRecord.Repository.RouteRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordUpdateService {

    RecordRepository recordRepository;
    @Autowired
    PhotoRecordRepository photoRecordRepository;
    @Autowired
    RouteRecordRepository routeRecordRepository;

    public RecordUpdateResDto updateRecordDetails(RecordUpdateReqDto request){
        // 사진 순서 업데이트 및 응답 생성
        List<PhotoUpdateResDto> photoResponses = request.getUpdatePhotos().stream()
                .map(photoUpdate -> {
                    PhotoRecordEntity photoRecord = photoRecordRepository.findById(photoUpdate.getPhotoId())
                            .orElseThrow(() -> new RecordNotFoundException(photoUpdate.getPhotoId()));
                    photoRecord.setSeq(photoUpdate.getNewSeq());
                    photoRecordRepository.save(photoRecord);

                    // 응답 생성
                    return PhotoUpdateResDto.builder()
                                .photoId(photoRecord.getPhotoRecordId())
                                .newSeq(photoRecord.getSeq())
                                .build();
                    })
                    .collect(Collectors.toList());

        // 경로 순서 업데이트 및 응답 생성
        List<RouteUpdateResDto> routeResponses = request.getUpdatedRoutes().stream()
                .map(routeUpdate -> {
                    RouteRecordEntity routeRecord = routeRecordRepository.findById(routeUpdate.getRouteId())
                            .orElseThrow(() -> new RecordNotFoundException(routeUpdate.getRouteId()));
                    routeRecord.setSeq(routeUpdate.getNewSeq());
                    routeRecordRepository.save(routeRecord);

                        // 응답 생성
                        return RouteUpdateResDto.builder()
                                .routeId(routeRecord.getRouteId())
                                .newSeq(routeRecord.getSeq())
                                .build();
                    })
                    .collect(Collectors.toList());

            // 필요에 따라 결과 반환
            return RecordUpdateResDto.builder()
                    .recordId(request.getRecordId())
                    .updatePhotos(photoResponses)
                    .updatedRoutes(routeResponses)
                    .build();
        }
    }
