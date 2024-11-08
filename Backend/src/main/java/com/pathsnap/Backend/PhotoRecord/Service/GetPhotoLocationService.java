package com.pathsnap.Backend.PhotoRecord.Service;

import com.pathsnap.Backend.PhotoRecord.Component.PhotoLocation.FilterPhotoLocation;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoLocationResDto;
import com.pathsnap.Backend.Record.Component.CheckRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPhotoLocationService {

    private final FilterPhotoLocation filterPhotoLocation;
    private final CheckRecord checkRecord;

    public List<PhotoLocationResDto> getPhotosWithinRadius(String userId, double lon, double lat, double radius) {

        //recordId들이 있는지 확인
        List<String> recordIds = checkRecord.exec2(userId);

        //user가 현재 보고있는 위치주변 기록 반환
        return filterPhotoLocation.exec(recordIds, lat, lon, radius);
    }
}