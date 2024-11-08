package com.pathsnap.Backend.RouteRecord.Component;

import com.pathsnap.Backend.Coordinate.Dto.Res.CoordinateResDto;
import com.pathsnap.Backend.RouteRecord.Dto.Res.RouteRecordResDto;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DetermineTransportMode{

    public void exec(List<RouteRecordResDto> routes) {
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
