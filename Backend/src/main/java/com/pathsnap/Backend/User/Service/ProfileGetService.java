package com.pathsnap.Backend.User.Service;

import com.pathsnap.Backend.Image.Component.ImageGet;
import com.pathsnap.Backend.Image.Dto.Res.ImageResDto;
import com.pathsnap.Backend.User.Compnent.UserCheck;
import com.pathsnap.Backend.User.Dto.Res.UserResDto;
import com.pathsnap.Backend.User.Entity.UserEntity;
import com.pathsnap.Backend.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileGetService {

    private final UserCheck userCheck;
    private final ImageGet imageGet;

    public UserResDto getProfile(String userId) {
        // 사용자 찾기 및 예외 처리
        UserEntity user = userCheck.exec(userId);

        // 사용자 이미지 조회 및 처리
        List<ImageResDto> images = imageGet.exec(user.getImage()); // 이미지 체크를 exec 메서드로 분리

        // UserResDTO 생성 및 반환
        return new UserResDto(
                user.getUserName(),
                user.getBirthDate(),
                user.getPhoneNumber(),
                images
        );
    }
}