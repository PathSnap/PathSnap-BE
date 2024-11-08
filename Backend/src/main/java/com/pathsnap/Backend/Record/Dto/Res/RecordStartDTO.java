package com.pathsnap.Backend.Record.Dto.Res;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecordStartDTO {
    private String recordId;

    public RecordStartDTO(String recordId){
        this.recordId = recordId;
    }
}
