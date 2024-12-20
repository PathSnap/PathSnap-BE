package com.pathsnap.Backend.User.Service;

import com.pathsnap.Backend.Image.Component.GetImage;
import com.pathsnap.Backend.Image.Dto.Res.ImageResDto;
import com.pathsnap.Backend.User.Compnent.CheckUser;
import com.pathsnap.Backend.User.Compnent.UpdateUserProfile;
import com.pathsnap.Backend.User.Dto.Req.UserUpdateReqDto;
import com.pathsnap.Backend.User.Dto.Res.UserResDto;
import com.pathsnap.Backend.User.Entity.User1Entity;
import com.pathsnap.Backend.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpdateProfileService {

    private final UserRepository userRepository;
    private final UpdateUserProfile userProfileUpdate;
    private final GetImage imageGet;
    private final CheckUser userCheck;

    public UserResDto updateProfile(String userId, UserUpdateReqDto userUpdateReqDto) {
        // 사용자 찾기 및 예외 처리
        User1Entity user = userCheck.exec(userId);

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
                user.getAddress(),
                user.getLat(),
                user.getLng(),
                images
        );
    }


}
