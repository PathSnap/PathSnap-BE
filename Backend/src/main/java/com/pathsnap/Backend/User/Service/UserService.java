package com.pathsnap.Backend.User.Service;

import com.pathsnap.Backend.Exception.UserNotFoundException;
import com.pathsnap.Backend.User.Dto.Res.UserResDTO;
import com.pathsnap.Backend.User.Entity.UserEntity;
import com.pathsnap.Backend.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResDTO getProfile(String userId) {

        // 사용자 찾기 및 예외 처리
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        // UserResDTO 생성 및 반환
        return new UserResDTO(
                user.getUserName(),
                user.getBirthDate(),
                user.getPhoneNumber()
        );
    }
}
