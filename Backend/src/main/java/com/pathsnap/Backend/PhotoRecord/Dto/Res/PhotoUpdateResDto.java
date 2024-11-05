package com.pathsnap.Backend.PhotoRecord.Dto.Res;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhotoUpdateResDto {
        private String photoId;
        private Number newSeq;

}
