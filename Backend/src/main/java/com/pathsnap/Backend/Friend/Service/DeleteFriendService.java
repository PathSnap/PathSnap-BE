package com.pathsnap.Backend.Friend.Service;

import com.pathsnap.Backend.Friend.Component.CheckFriend;
import com.pathsnap.Backend.Friend.Repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteFriendService {

    private final CheckFriend checkFriend;
    private final FriendRepository friendRepository;

    public void deleteFriend(String friendId) {

        //friendId 있는지 확인
        checkFriend.exec(friendId);

        //record 삭제
        friendRepository.deleteById(friendId);

    }
}