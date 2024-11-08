package com.pathsnap.Backend.Record.Service;

import com.pathsnap.Backend.PhotoRecord.Component.GetPhotoRecord;
import com.pathsnap.Backend.PhotoRecord.Component.CheckPhotoRecord;
import com.pathsnap.Backend.PhotoRecord.Repository.PhotoRecordRepository;
import com.pathsnap.Backend.Record.Component.CheckRecord;
import com.pathsnap.Backend.Record.Dto.Res.RecordDetailResDto;
import com.pathsnap.Backend.Record.Entity.RecordEntity;
import com.pathsnap.Backend.RouteRecord.Component.DetermineTransportMode;
import com.pathsnap.Backend.RouteRecord.Component.GetRouteRecord;
import com.pathsnap.Backend.RouteRecord.Repository.RouteRecordRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class RecordDetailService {

    private final CheckRecord recordCheck;
    private final GetPhotoRecord getPhotoRecord;
    private final GetRouteRecord getRouteRecord;
    private final CheckPhotoRecord photoRecordCheck;
    private final DetermineTransportMode determineTransportMode;

    private final PhotoRecordRepository photoRecordRepository;

    private final RouteRecordRepository routeRecordRepository;

    public RecordDetailResDto getRecordDetail(String recordId) {

        // 기록ID 있는지 확인
        RecordEntity record = recordCheck.exec(recordId);

        // 기록 조회 반환
        RecordDetailResDto response = RecordDetailResDto.builder()
                .recordId(record.getRecordId())
                .recordName(record.getRecordName())
                .isGroup(record.isRecordIsGroup())
                .photoRecords(getPhotoRecord.exec(recordId)) // 포토기록을 조회
                .routeRecords(getRouteRecord.exec(recordId)) // 경로기록을 조회
                .build();

        // 각 경로의 이동 수단 결정
        determineTransportMode.exec(getRouteRecord.exec(recordId));

        return response;
    }
}
