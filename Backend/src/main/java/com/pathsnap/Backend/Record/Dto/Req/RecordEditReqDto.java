package com.pathsnap.Backend.Record.Dto.Req;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecordEditReqDto {
    private String recordId;
    private String recordName;
}
