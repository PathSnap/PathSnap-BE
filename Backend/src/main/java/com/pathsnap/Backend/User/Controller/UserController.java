package com.pathsnap.Backend.User.Controller;

import com.pathsnap.Backend.User.Dto.Res.LocationResDTO;
import com.pathsnap.Backend.User.Dto.Req.UserUpdateReqDTO;
import com.pathsnap.Backend.User.Dto.Res.UserResDTO;
import com.pathsnap.Backend.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
public class UserController {

    @Autowired
    private UserService userService;

    // 프로필 정보 불러오기
    @GetMapping("/{userId}")
    public UserResDTO getProfile(@PathVariable String userId) {
        return userService.getProfile(userId);
    }

    // 프로필 정보 수정
    @PatchMapping("/{userId}")
    public UserResDTO updateProfile(
            @PathVariable String userId,
            @RequestBody UserUpdateReqDTO userUpdateReqDTO) {
        return userService.updateProfile(userId, userUpdateReqDTO);
    }

    // 여행 이미지 가져오기
    @GetMapping("/location/{userId}")
    public LocationResDTO getLocation(@PathVariable String userId) {
        return userService.getLocations(userId);
    }
}
