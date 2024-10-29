package com.pathsnap.Backend.Record.Dto.Get.Res;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RecordStartDTO {
    private String recordId;

    @Builder
    public RecordStartDTO(String recordId){
        this.recordId = recordId;
    }
}
