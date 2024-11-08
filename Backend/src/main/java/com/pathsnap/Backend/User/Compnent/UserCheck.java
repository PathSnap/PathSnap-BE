package com.pathsnap.Backend.User.Compnent;

import com.pathsnap.Backend.Exception.RouteRecordNotFoundException;
import com.pathsnap.Backend.RouteRecord.Entity.RouteRecordEntity;
import com.pathsnap.Backend.User.Entity.UserEntity;
import com.pathsnap.Backend.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCheck {
    private final UserRepository userRepository;
    public UserEntity exec(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RouteRecordNotFoundException(userId));
    }
}
