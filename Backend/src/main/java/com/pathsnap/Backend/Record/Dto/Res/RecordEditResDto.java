package com.pathsnap.Backend.Record.Dto.Res;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecordEditResDto {
    private String recordId;
    private String recordName;
}
