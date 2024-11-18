package com.pathsnap.Backend.Friend.Controller;

import com.pathsnap.Backend.Friend.Dto.Res.FriendAddResDto;
import com.pathsnap.Backend.Friend.Dto.Res.FriendListResDto;
import com.pathsnap.Backend.Friend.Dto.Res.FriendSearchResDto;
import com.pathsnap.Backend.Friend.Service.AddFriendService;
import com.pathsnap.Backend.Friend.Service.DeleteFriendService;
import com.pathsnap.Backend.Friend.Service.ListFriendService;
import com.pathsnap.Backend.Friend.Service.SearchFriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friends")

public class FriendController {
    private final SearchFriendService searchFriendService;
    private final AddFriendService addFriendService;
    private final ListFriendService listFriendService;
    private final DeleteFriendService deleteFriendService;

    //유저 목록을 이름으로 조회
    @GetMapping("/search/{name}")
    public ResponseEntity<List<FriendSearchResDto>> searchFriends(@PathVariable String name) {

        return ResponseEntity.ok(searchFriendService.searchFriends(name));
    }

    //유저 아이디로 기록에 친구 추가
    @PostMapping("/{userId}/{recordId}")
    public ResponseEntity<FriendAddResDto> addFriendToRecord(@PathVariable String userId, @PathVariable String recordId) {

        FriendAddResDto response = addFriendService.addFriendToRecord(userId, recordId);
        return ResponseEntity.ok(response);
    }

    //기록 아이디에 속한 친구들 조회
    @GetMapping("/{recordId}")
    public ResponseEntity<FriendListResDto> getFriendRecord(@PathVariable String recordId) {

        return ResponseEntity.ok(listFriendService.getFriendRecordByRecordId(recordId));
    }

    //친구 삭제
    @DeleteMapping("/delete/{friendId}")
    public ResponseEntity<Void> deleteFriend(@PathVariable String friendId) {

        deleteFriendService.deleteFriend(friendId);
        return ResponseEntity.ok().build();
    }
}