package com.pathsnap.Backend.Friend.Component;

import com.pathsnap.Backend.Exception.FriendNotFoundException;
import com.pathsnap.Backend.Friend.Entity.Friend1Entity;
import com.pathsnap.Backend.Friend.Repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckFriend {

    private final FriendRepository friendRepository;
    public Friend1Entity exec(String friendId) {

        //친구 아이디 있는지 확인
        return friendRepository.findById(friendId)
                .orElseThrow(() -> new FriendNotFoundException(friendId));
    }
}
