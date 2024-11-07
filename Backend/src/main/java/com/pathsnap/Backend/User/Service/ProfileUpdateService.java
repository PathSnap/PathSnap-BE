package com.pathsnap.Backend.User.Service;

import com.pathsnap.Backend.Image.Component.ImageGet;
import com.pathsnap.Backend.Image.Dto.Res.ImageResDto;
import com.pathsnap.Backend.Image.Repository.ImageRepository;
import com.pathsnap.Backend.User.Compnent.UserCheck;
import com.pathsnap.Backend.User.Compnent.UserProfileUpdate;
import com.pathsnap.Backend.User.Dto.Req.UserUpdateReqDto;
import com.pathsnap.Backend.User.Dto.Res.UserResDto;
import com.pathsnap.Backend.User.Entity.UserEntity;
import com.pathsnap.Backend.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileUpdateService {

    private final UserRepository userRepository;
    private final UserProfileUpdate userProfileUpdate;
    private final ImageGet imageGet;
    private final UserCheck userCheck;

    public UserResDto updateProfile(String userId, UserUpdateReqDto userUpdateReqDto) {
        // 사용자 찾기 및 예외 처리
        UserEntity user = userCheck.exec(userId);

        // 사용자 정보 업데이트
        userProfileUpdate.exec(user, userUpdateReqDto);

        // 변경된 사용자 저장
        userRepository.save(user);

        // 사용자 이미지 정보 처리
        List<ImageResDto> images = imageGet.exec(user.getImage());

        // 변경된 사용자 정보 반환
        return new UserResDto(
                user.getUserName(),
                user.getBirthDate(),
                user.getPhoneNumber(),
                images
        );
    }


}
