package com.pathsnap.Backend.User.Service;

import com.pathsnap.Backend.Image.Component.GetImage;
import com.pathsnap.Backend.Image.Dto.Res.ImageResDto;
import com.pathsnap.Backend.User.Compnent.CheckUser;
import com.pathsnap.Backend.User.Dto.Res.UserResDto;
import com.pathsnap.Backend.User.Entity.User1Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProfileService {

    private final CheckUser userCheck;
    private final GetImage imageGet;

    public UserResDto getProfile(String userId) {
        // 사용자 찾기 및 예외 처리
        User1Entity user = userCheck.exec(userId);

        // 사용자 이미지 조회 및 처리
        List<ImageResDto> images = imageGet.exec(user.getImage()); // 이미지 체크를 exec 메서드로 분리

        // UserResDTO 생성 및 반환
        return new UserResDto(
                user.getUserName(),
                user.getBirthDate(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getLat(),
                user.getLng(),
                images
        );
    }
}