package com.pathsnap.Backend.Friend.Service;

import com.pathsnap.Backend.Friend.Component.AddFriend;
import com.pathsnap.Backend.Friend.Dto.Res.FriendAddResDto;
import com.pathsnap.Backend.Friend.Entity.Friend1Entity;
import com.pathsnap.Backend.Record.Component.CheckRecord;
import com.pathsnap.Backend.Record.Entity.Record1Entity;
import com.pathsnap.Backend.User.Compnent.CheckUser;
import com.pathsnap.Backend.User.Entity.User1Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddFriendService {
    private final AddFriend addFriend;
    private final CheckUser checkUser;
    private final CheckRecord checkRecord;
    public FriendAddResDto addFriendToRecord(String userId, String recordId){

        //User와 Record 확인
        User1Entity user = checkUser.exec(userId);
        Record1Entity record = checkRecord.exec(recordId);

        //친구 추가 
        Friend1Entity friend = addFriend.exec(user,record);

        //Dto 생성 및 반환
        return FriendAddResDto.builder()
                .friendId(friend.getFriendId())
                .name(friend.getUser().getUserName())
                .imageId(friend.getUser().getImage().getImageId())
                .url(friend.getUser().getImage().getUrl())
                .build();
    }
}
