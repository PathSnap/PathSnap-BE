package com.pathsnap.Backend.User.Dto.Res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserStoryResDto {
    private String userId;
    private String userName;
    private String imageId;
    private String url;
}
