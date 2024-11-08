package com.pathsnap.Backend.RouteRecord.Service;

import com.pathsnap.Backend.Coordinate.Component.AddCoordinate;
import com.pathsnap.Backend.Coordinate.Component.CreateCoordinate;
import com.pathsnap.Backend.Coordinate.Entitiy.CoordinateEntity;
import com.pathsnap.Backend.Coordinate.Repository.CoordinateRepository;
import com.pathsnap.Backend.RouteRecord.Component.CheckRouteRecord;
import com.pathsnap.Backend.RouteRecord.Dto.Req.RouteReqDto;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecordEntity;
import com.pathsnap.Backend.RouteRecord.Repository.RouteRecordRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class EditRouteRecordService {

    private final CheckRouteRecord routeRecordCheck;
    private final CreateCoordinate createCoordinate;
    private final AddCoordinate addCoordinate;
    private final CoordinateRepository coordinateRepository;
    private final RouteRecordRepository routeRecordRepository;

    public void editRoute(RouteReqDto routeReqDto) {

        //routeRecord 조회
        RouteRecordEntity routeRecord = routeRecordCheck.exec(routeReqDto.getRouteId());

        //new coordinate 생성
        CoordinateEntity newCoordinate = createCoordinate.exec(routeReqDto.getCoordinateReqDto(), routeRecord);

        //coordinate 저장
        coordinateRepository.save(newCoordinate);

        //coordinate에 좌표 추가
        addCoordinate.exec(routeRecord, newCoordinate);

        //Edited routeRecord 저장
        routeRecordRepository.save(routeRecord);

    }
}
