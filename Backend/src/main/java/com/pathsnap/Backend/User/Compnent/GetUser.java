package com.pathsnap.Backend.User.Compnent;

import com.pathsnap.Backend.User.Entity.UserEntity;
import com.pathsnap.Backend.User.Repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class GetUser {
    UserRepository userRepository;
    public UserEntity findByUserId(String userId){
        return userRepository.findById(userId).get();
    }


}
