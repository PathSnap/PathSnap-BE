package com.pathsnap.Backend.RouteRecord.Service;

import com.pathsnap.Backend.Coordinate.Entitiy.CoordinateEntity;
import com.pathsnap.Backend.RouteRecord.Dto.Req.RouteReqDto;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecordEntity;
import com.pathsnap.Backend.RouteRecord.Repository.RouteRecordRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Builder
public class RouteRecordSaveService {

    @Autowired
    private RouteRecordRepository routeRecordRepository;

    public void saveRoute(RouteReqDto routeReqDto) {

        //경로 기록을 조회
        RouteRecordEntity routeRecord = routeRecordRepository.findById(routeReqDto.getRouteId())
                .orElseThrow(() -> new RuntimeException("RouteRecord not found"));

        //새로운 좌표 생성
        CoordinateEntity newCoordinate = CoordinateEntity.builder()
                .coordinateId(UUID.randomUUID().toString())
                .lat(routeReqDto.getCoordinateReqDto().getLat())
                .lng(routeReqDto.getCoordinateReqDto().getLng())
                .routeRecord(routeRecord)
                .build();

        //경로 추가 로직
        routeRecord.addCoordinate(newCoordinate);

        //변경 사항 저장
        routeRecordRepository.save(routeRecord);

    }
}
