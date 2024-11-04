package com.pathsnap.Backend.User.Controller;

import com.pathsnap.Backend.User.Dto.Res.CalendarResDTO;
import com.pathsnap.Backend.User.Dto.Res.LocationResDTO;
import com.pathsnap.Backend.User.Dto.Req.UserUpdateReqDTO;
import com.pathsnap.Backend.User.Dto.Res.UserResDTO;
import com.pathsnap.Backend.User.Service.CalendarListService;
import com.pathsnap.Backend.User.Service.LocationService;
import com.pathsnap.Backend.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private CalendarListService calendarService;


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
        return locationService.getLocations(userId);
    }

    // 달력 불러오기
    @GetMapping("/calendar/{userId}/{month}")
    public CalendarResDTO getCalendar(@PathVariable String userId, @PathVariable int month) {
        return calendarService.getCalendar(userId, month);
    }
}