package com.pathsnap.Backend.Record.Dto.Req;

import com.pathsnap.Backend.PhotoRecord.Dto.Req.PhotoUpdateReqDto;
import com.pathsnap.Backend.RouteRecord.Dto.Req.RouteUpdateReqDto;
import lombok.Data;

import java.util.List;

@Data
public class RecordUpdateReqDto {
    private String recordId;
    private List<PhotoUpdateReqDto> updatePhotos;
    private List<RouteUpdateReqDto> updatedRoutes;
}
