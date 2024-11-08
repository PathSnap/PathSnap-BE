package com.pathsnap.Backend.User.Compnent;

import com.pathsnap.Backend.Image.Component.CheckImage;
import com.pathsnap.Backend.Image.Entity.ImageEntity;
import com.pathsnap.Backend.User.Dto.Req.UserUpdateReqDto;
import com.pathsnap.Backend.User.Entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateUserProfile {

    private final CheckImage imageCheck;

    // 사용자 정보 업데이트 메서드
    public void exec(UserEntity user, UserUpdateReqDto userUpdateReqDto) {
        if (userUpdateReqDto.getUserName() != null) {
            user.setUserName(userUpdateReqDto.getUserName());
        }
        if (userUpdateReqDto.getBirthDate() != null) {
            user.setBirthDate(userUpdateReqDto.getBirthDate());
        }
        if (userUpdateReqDto.getPhoneNumber() != null) {
            user.setPhoneNumber(userUpdateReqDto.getPhoneNumber());
        }
        if (userUpdateReqDto.getImageId() != null) {
            ImageEntity image = imageCheck.exec(userUpdateReqDto.getImageId());
            user.setImage(image);
        }
    }
}
