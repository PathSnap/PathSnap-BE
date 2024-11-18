package com.pathsnap.Backend.Friend.Service;

import com.pathsnap.Backend.Friend.Dto.Res.FriendAddResDto;
import com.pathsnap.Backend.Friend.Dto.Res.FriendListResDto;
import com.pathsnap.Backend.Record.Component.GetRecord;
import com.pathsnap.Backend.Record.Entity.Record1Entity;
import com.pathsnap.Backend.User.Dto.Res.UserStoryResDto;
import com.pathsnap.Backend.User.Entity.User1Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListFriendService {

    private final GetRecord getRecord;

    public FriendListResDto getFriendRecordByRecordId(String recordId) {

        //기록 조회
        Record1Entity record = getRecord.WithUserAndFriends(recordId);

        //유저 정보 매핑
        User1Entity user = record.getUser();
        UserStoryResDto userStoryResDto = UserStoryResDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .imageId(user.getImage().getImageId())
                .url(user.getImage().getUrl())
                .build();

        //친구 목록 매핑
        List<FriendAddResDto> friendAddResDtos = record.getFriends().stream()
                .map(friend -> FriendAddResDto.builder()
                        .friendId(friend.getFriendId())
                        .name(friend.getUser().getUserName())
                        .imageId(friend.getUser().getImage().getImageId())
                        .url(friend.getUser().getImage().getUrl())
                        .build()
                )
                .toList();

        return new FriendListResDto(userStoryResDto,friendAddResDtos);
    }
}
