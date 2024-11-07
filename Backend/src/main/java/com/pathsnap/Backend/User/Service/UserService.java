package com.pathsnap.Backend.User.Service;

import com.pathsnap.Backend.Exception.ImageNotFoundException;
import com.pathsnap.Backend.Exception.UserNotFoundException;
import com.pathsnap.Backend.Image.Entity.ImageEntity;
import com.pathsnap.Backend.Image.Repository.ImageRepository;
import com.pathsnap.Backend.S3.Dto.Res.S3ResDto;
import com.pathsnap.Backend.User.Dto.Req.UserUpdateReqDto;
import com.pathsnap.Backend.User.Dto.Res.UserResDto;
import com.pathsnap.Backend.User.Entity.UserEntity;
import com.pathsnap.Backend.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageRepository imageRepository;


    public UserResDto getProfile(String userId) {

        // 사용자 찾기 및 예외 처리
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        // 사용자 이미지
        List<S3ResDto> images = Collections.emptyList();

        if (user.getImage() != null) {
            images = List.of(new S3ResDto(user.getImage().getImageId(), user.getImage().getUrl()));
        }

        // UserResDTO 생성 및 반환
        return new UserResDto(
                user.getUserName(),
                user.getBirthDate(),
                user.getPhoneNumber(),
                images
        );
    }

    public UserResDto updateProfile(String userId, UserUpdateReqDto userUpdateReqDto) {

        // 사용자 찾기 및 예외 처리
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        // 필요한 필드만 업데이트
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
            ImageEntity image = imageRepository.findById(userUpdateReqDto.getImageId())
                    .orElseThrow(() -> new ImageNotFoundException(userUpdateReqDto.getImageId()));
            user.setImage(image);
        }

        // 변경된 엔티티 저장
        userRepository.save(user);

        // 사용자 이미지
        List<S3ResDto> images = Collections.emptyList();
        if (user.getImage() != null) {
            images = List.of(new S3ResDto(user.getImage().getImageId(), user.getImage().getUrl()));
        }

        // UserResDto 반환
        return new UserResDto(
                user.getUserName(),
                user.getBirthDate(),
                user.getPhoneNumber(),
                images

        );
    }
}
