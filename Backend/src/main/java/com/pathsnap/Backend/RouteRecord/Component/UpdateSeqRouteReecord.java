package com.pathsnap.Backend.RouteRecord.Component;

import com.pathsnap.Backend.RouteRecord.Dto.Req.RouteUpdateReqDto;
import com.pathsnap.Backend.RouteRecord.Dto.Res.RouteUpdateResDto;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecordEntity;
import com.pathsnap.Backend.RouteRecord.Repository.RouteRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UpdateSeqRouteReecord {
    
    private final CheckRouteRecord routeRecordCheck;
    private final RouteRecordRepository routeRecordRepository;
    public List<RouteUpdateResDto> exec(List<RouteUpdateReqDto> updateRoutes) {
        return updateRoutes.stream()
                .map(routeUpdate -> {
                    RouteRecordEntity routeRecord = routeRecordCheck.exec(routeUpdate.getRouteId());
                    routeRecord.setSeq(routeUpdate.getNewSeq());
                    routeRecordRepository.save(routeRecord);

                    return RouteUpdateResDto.builder()
                            .routeId(routeRecord.getRouteId())
                            .newSeq(routeRecord.getSeq())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
