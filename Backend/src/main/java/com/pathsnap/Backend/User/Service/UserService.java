package com.pathsnap.Backend.User.Service;

import com.pathsnap.Backend.Exception.UserNotFoundException;
import com.pathsnap.Backend.Image.Repository.ImageRepository;
import com.pathsnap.Backend.S3.Dto.Res.S3ResDTO;
import com.pathsnap.Backend.User.Dto.Req.UserUpdateReqDTO;
import com.pathsnap.Backend.User.Dto.Res.UserResDTO;
import com.pathsnap.Backend.User.Dto.Res.UserResTestDTO;
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

    public UserResDTO getProfile(String userId) {

        // 사용자 찾기 및 예외 처리
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        // 사용자 이미지
        List<S3ResDTO> images = Collections.emptyList();

        if (user.getImage() != null) {
            images = List.of(new S3ResDTO(user.getImage().getImageId(), user.getImage().getUrl()));
        }

        // UserResDTO 생성 및 반환
        return new UserResDTO(
                user.getUserName(),
                user.getBirthDate(),
                user.getPhoneNumber(),
                images
        );
    }

    public UserResTestDTO updateProfile(String userId, UserUpdateReqDTO userUpdateReqDTO) {

        // 사용자 찾기 및 예외 처리
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        // 필요한 필드만 업데이트
        if (userUpdateReqDTO.getUserName() != null) {
            user.setUserName(userUpdateReqDTO.getUserName());
        }
        if (userUpdateReqDTO.getBirthDate() != null) {
            user.setBirthDate(userUpdateReqDTO.getBirthDate());
        }
        if (userUpdateReqDTO.getPhoneNumber() != null) {
            user.setPhoneNumber(userUpdateReqDTO.getPhoneNumber());
        }

        // 변경된 엔티티 저장
        userRepository.save(user);

        // 업데이트된 정보를 UserResDTO로 변환하여 반환
        return new UserResTestDTO(
                user.getUserName(),
                user.getBirthDate(),
                user.getPhoneNumber()
        );
    }


}
