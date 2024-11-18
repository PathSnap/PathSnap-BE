package com.pathsnap.Backend.Friend.Dto.Res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendAddResDto {
    private String friendId;
    private String name;
    private String imageId;
    private String url;
}
