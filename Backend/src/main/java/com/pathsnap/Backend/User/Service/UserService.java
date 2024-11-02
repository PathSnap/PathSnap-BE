package com.pathsnap.Backend.User.Service;

import com.pathsnap.Backend.Exception.UserNotFoundException;
import com.pathsnap.Backend.Image.Repository.ImageRepository;
import com.pathsnap.Backend.S3.Dto.Res.S3ResDTO;
import com.pathsnap.Backend.User.Dto.Res.UserResDTO;
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
}
