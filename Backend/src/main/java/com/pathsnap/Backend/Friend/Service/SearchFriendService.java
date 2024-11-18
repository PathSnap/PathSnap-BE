package com.pathsnap.Backend.Friend.Service;

import com.pathsnap.Backend.Friend.Dto.Res.FriendSearchResDto;
import com.pathsnap.Backend.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchFriendService {
    private final UserRepository userRepository;

    public List<FriendSearchResDto> searchFriends(String userName){

        //유저 이름을 통해 같은 이름의 유저 리스트 조회
        return userRepository.findByUserName(userName).stream()
                .map(user -> FriendSearchResDto.builder()
                        .userId(user.getUserId())
                        .name(user.getUserName())
                        .imageId(user.getImage().getImageId())
                        .url(user.getImage().getUrl())
                        .phoneNumber(user.getPhoneNumber())
                        .build())
                .collect(Collectors.toList());

    }
}
