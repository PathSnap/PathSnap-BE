package com.pathsnap.Backend.Friend.Dto.Res;

import com.pathsnap.Backend.User.Dto.Res.UserStoryResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendListResDto {
    private UserStoryResDto user;
    private List<FriendAddResDto> friends;
}
