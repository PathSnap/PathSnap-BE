package com.pathsnap.Backend.Friend.Component;

import com.pathsnap.Backend.Friend.Entity.Friend1Entity;
import com.pathsnap.Backend.Friend.Repository.FriendRepository;
import com.pathsnap.Backend.Record.Entity.Record1Entity;
import com.pathsnap.Backend.User.Entity.User1Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AddFriend {
    private final FriendRepository friendRepository;

    public Friend1Entity exec(User1Entity user, Record1Entity record){

        //친구를 기록에 추가 및 저장
        Friend1Entity friend = Friend1Entity.builder()
                .friendId(UUID.randomUUID().toString())
                .record(record)
                .user(user)
                .build();

        return friendRepository.save(friend);
    }
}
