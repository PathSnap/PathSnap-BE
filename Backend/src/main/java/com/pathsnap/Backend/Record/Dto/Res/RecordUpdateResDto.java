package com.pathsnap.Backend.Record.Dto.Res;

import com.pathsnap.Backend.PhotoRecord.Dto.Req.PhotoUpdateReqDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoUpdateResDto;
import com.pathsnap.Backend.RouteRecord.Dto.Req.RouteUpdateReqDto;
import com.pathsnap.Backend.RouteRecord.Dto.Res.RouteUpdateResDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RecordUpdateResDto {

    private String recordId;
    private List<PhotoUpdateResDto> updatePhotos;
    private List<RouteUpdateResDto> updatedRoutes;
}
