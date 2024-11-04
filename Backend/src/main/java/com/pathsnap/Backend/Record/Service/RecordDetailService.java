package com.pathsnap.Backend.Record.Service;

import com.pathsnap.Backend.Coordinate.Dto.Res.CoordinateResDto;
import com.pathsnap.Backend.Exception.RecordNotFoundException;
import com.pathsnap.Backend.Image.Dto.Req.ImageReqDto;
import com.pathsnap.Backend.Image.Entity.ImageEntity;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoRecordResDto;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecordEntity;
import com.pathsnap.Backend.PhotoRecord.Repository.PhotoRecordRepository;
import com.pathsnap.Backend.Record.Dto.Res.RecordDetailResDto;
import com.pathsnap.Backend.Record.Entity.RecordEntity;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import com.pathsnap.Backend.RouteRecord.Dto.Res.RouteRecordResDto;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecordEntity;
import com.pathsnap.Backend.RouteRecord.Repository.RouteRecordRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Nodes.collect;

@Service
@Builder
public class RecordDetailService {

    @Autowired
    private RecordRepository recordRepository;
    @Autowired
    private PhotoRecordRepository photoRecordRepository;;
    @Autowired
    private RouteRecordRepository routeRecordRepository;

    public RecordDetailResDto getRecordDetail(String recordId) {

        //기록 조회
        RecordEntity record = recordRepository.findById(recordId)
                .orElseThrow(() -> new RecordNotFoundException(recordId));

        //사진 기록 조회
        List<PhotoRecordEntity> photoRecords = photoRecordRepository.findByRecord_RecordId(recordId);

        List<PhotoRecordResDto> photoRecordResDto = photoRecords.stream()
                .map(photoRecord -> {
                    List<ImageReqDto> imageUrls = photoRecord.getImagePhotos() // ImagePhotoEntity 리스트 가져오기
                            .stream()
                            .map(imagePhoto -> {
                                ImageEntity image = imagePhoto.getImage(); // ImageEntity 가져오기
                                return ImageReqDto.builder()
                                        .imageId(image.getImageId())
                                        .url(image.getUrl()).build(); // imageId와 URL 가져오기
                            })
                            .collect(Collectors.toList());

                    return PhotoRecordResDto.builder()
                            .photoId(photoRecord.getPhotoRecordId())
                            .seq(photoRecord.getSeq())
                            .images(imageUrls) // 이미지 리스트 설정
                            .photoTitle(photoRecord.getPhotoTitle())
                            .photoContent(photoRecord.getPhotoContent())
                            .photoDate(photoRecord.getPhotoDate().toString())
                            .lat(photoRecord.getLat())
                            .lng(photoRecord.getLng())
                            .build();
                })
                .collect(Collectors.toList());



        //경로 기록 조회
        //List<RouteRecordResDto> routeRecords = routeRecordRepository.findByRecord_RecordId(recordId);

        List<RouteRecordEntity> routeRecords = routeRecordRepository.findByRecord_RecordId(recordId);
        List<RouteRecordResDto> routeRecordResDto = routeRecords.stream()
                .map(routeRecord -> {
                    List<CoordinateResDto> coordinateResDtos = routeRecord.getCoordinates()
                            .stream()
                            .map(coordinate -> { CoordinateResDto.builder()
                                        .lat(coordinate.getLat())
                                        .lng(coordinate.getLng())
                                        .timeStamp(coordinate.getTimestamp()) // assuming there is a getTimestamp() method
                                        .build())
                                .collect


                            //DTO 매핑
                            RecordDetailResDto response = RecordDetailResDto.builder()
                                    .recordId(record.getRecordId())
                                    .recordName(record.getRecordName())
                                    .isGroup(record.isRecordIsGroup())
                                    .photoRecords(photoRecordResDto)
                                    .routeRecords(routeRecordResDto)
                                    .build();


                // 각 경로의 이동 수단 결정
                determineTransportMode(routeRecordResDto);

        return response;
    }

    private void determineTransportMode(List<RouteRecordResDto> routes) {
        for (RouteRecordResDto route : routes) {
            List<CoordinateResDto> coordinates = route.getCoordinates();

            // 좌표 리스트가 비어있지 않은 경우에만 이동 수단 결정
            if (coordinates.size() > 1) {
                double totalDistance = 0;
                double totalTime = 0;

                // 좌표 간 거리 및 시간 차이를 누적하여 속도 계산
                for (int i = 1; i < coordinates.size(); i++) {
                    CoordinateResDto prev = coordinates.get(i - 1);
                    CoordinateResDto current = coordinates.get(i);

                    // 거리 계산 (단위: 미터)
                    double distance = calculateDistance(prev.getLat(), prev.getLng(), current.getLat(), current.getLng());
                    totalDistance += distance;

                    // 시간 차이 계산 (단위: 초)
                    double timeDifference = (current.getTimeStamp().getTime() - prev.getTimeStamp().getTime()) / 1000.0;
                    totalTime += timeDifference;
                }

                // 평균 속도 계산 (m/s)
                double averageSpeed = totalDistance / totalTime;

                // 평균 속도를 기준으로 이동 수단 결정
                if (averageSpeed < 1.4) {  // 걷기 속도 기준 (1.4 m/s 이하)
                    route.setTransportMode("Walking");
                } else {  // 그 외는 차로 간주
                    route.setTransportMode("Car");
                }
            }
        }
    }

    private double calculateDistance(double lat1, double lng1, double lat2, double lng2) {
        final int EARTH_RADIUS = 6371; // 지구 반지름 (단위: km)
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c * 1000; // 거리 (단위: 미터)
    }
}


