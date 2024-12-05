package com.pathsnap.Backend.Friend.Service;

import com.pathsnap.Backend.Friend.Dto.Res.FriendSearchResDto;
import com.pathsnap.Backend.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.naming.SelectorContext.prefix;

@Service
@RequiredArgsConstructor
public class SearchFriendService {
    private final UserRepository userRepository;

    public List<FriendSearchResDto> searchFriends(String userName){

        // 이름이 특정 문자로 시작하는 유저 리스트 조회
        return userRepository.findByUserNameStartingWith(userName).stream()
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
