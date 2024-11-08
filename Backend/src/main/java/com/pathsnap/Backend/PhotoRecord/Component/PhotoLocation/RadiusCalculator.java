package com.pathsnap.Backend.PhotoRecord.Component.PhotoLocation;

import org.springframework.stereotype.Component;

@Component
public class RadiusCalculator {
    public boolean isWithinRadius(double photoLat, double photoLng, double lat, double lon, double radius) {
        double earthRadius = 6371e3; // 지구 반경 (미터)
        double dLat = Math.toRadians(lat - photoLat);
        double dLon = Math.toRadians(lon - photoLng);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(photoLat)) * Math.cos(Math.toRadians(lat)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = earthRadius * c; // 두 좌표 간 거리 계산
        return distance <= radius;
    }
}
