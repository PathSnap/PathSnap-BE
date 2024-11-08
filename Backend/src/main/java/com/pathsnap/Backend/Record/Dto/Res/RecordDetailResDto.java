package com.pathsnap.Backend.Record.Dto.Res;

import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoDataResDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoRecordResDto;
import com.pathsnap.Backend.RouteRecord.Dto.Res.RouteRecordResDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class RecordDetailResDto {
    private String recordId;
    private String recordName;
    private boolean isGroup;
    private List<PhotoRecordResDto> photoRecords;
    private List<RouteRecordResDto> routeRecords;
}
