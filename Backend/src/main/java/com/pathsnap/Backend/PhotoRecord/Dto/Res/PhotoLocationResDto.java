package com.pathsnap.Backend.PhotoRecord.Dto.Res;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PhotoLocationResDto {
    private String recordId;
    private List<PhotoDataResDto> photos;

}
