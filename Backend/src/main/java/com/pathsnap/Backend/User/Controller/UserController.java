package com.pathsnap.Backend.User.Controller;

import com.pathsnap.Backend.User.Dto.Res.CalendarResDto;
import com.pathsnap.Backend.User.Dto.Res.LocationResDto;
import com.pathsnap.Backend.User.Dto.Req.UserUpdateReqDto;
import com.pathsnap.Backend.User.Dto.Res.UserResDto;
import com.pathsnap.Backend.User.Service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profiles")
public class UserController {

    private final ProfileGetService profileGetService;
    private final ProfileUpdateService profileUpdateService;

    private final GetLocationService locationService;

    private final CalendarListService calendarService;


    // 프로필 정보 불러오기
    @GetMapping("/{userId}")
    public ResponseEntity<UserResDto> getProfile(@PathVariable String userId) {
        return ResponseEntity.ok(profileGetService.getProfile(userId));
    }

    // 프로필 정보 수정
    @PatchMapping("/{userId}")
    public ResponseEntity<UserResDto> updateProfile(
            @PathVariable String userId,
            @RequestBody UserUpdateReqDto userUpdateReqDTO) {
        return ResponseEntity.ok(profileUpdateService.updateProfile(userId, userUpdateReqDTO));
    }

    // 여행 이미지 가져오기
    @GetMapping("/location/{userId}")
    public ResponseEntity<LocationResDto> getLocation(@PathVariable String userId) {
        return ResponseEntity.ok(locationService.getLocations(userId));
    }

    // 달력 불러오기
    @GetMapping("/calendar/{userId}/{month}")
    public ResponseEntity<CalendarResDto> getCalendar(@PathVariable String userId, @PathVariable int month) {
        return ResponseEntity.ok(calendarService.getCalendar(userId, month));
    }
}
