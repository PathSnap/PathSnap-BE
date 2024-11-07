package com.pathsnap.Backend.User.Controller;

import com.pathsnap.Backend.User.Dto.Res.CalendarResDto;
import com.pathsnap.Backend.User.Dto.Res.LocationResDto;
import com.pathsnap.Backend.User.Dto.Req.UserUpdateReqDto;
import com.pathsnap.Backend.User.Dto.Res.UserResDto;
import com.pathsnap.Backend.User.Service.CalendarListService;
import com.pathsnap.Backend.User.Service.LocationService;
import com.pathsnap.Backend.User.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profiles")
public class UserController {

    private final UserService userService;

    private final LocationService locationService;

    private final CalendarListService calendarService;


    // 프로필 정보 불러오기
    @GetMapping("/{userId}")
    public UserResDto getProfile(@PathVariable String userId) {
        return userService.getProfile(userId);
    }

    // 프로필 정보 수정
    @PatchMapping("/{userId}")
    public UserResDto updateProfile(
            @PathVariable String userId,
            @RequestBody UserUpdateReqDto userUpdateReqDTO) {
        return userService.updateProfile(userId, userUpdateReqDTO);
    }

    // 여행 이미지 가져오기
    @GetMapping("/location/{userId}")
    public LocationResDto getLocation(@PathVariable String userId) {
        return locationService.getLocations(userId);
    }

    // 달력 불러오기
    @GetMapping("/calendar/{userId}/{month}")
    public CalendarResDto getCalendar(@PathVariable String userId, @PathVariable int month) {
        return calendarService.getCalendar(userId, month);
    }
}
