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
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/profiles")
public class UserController implements UserControllerDocs{

    private final GetProfileService profileGetService;
    private final UpdateProfileService profileUpdateService;

    private final GetUserLocationService getLocationService;

    private final GetCalendarService calendarService;


    // 프로필 정보 불러오기
    @GetMapping("/{userId}")
    public ResponseEntity<UserResDto> getProfile(@PathVariable String userId) {
        return ResponseEntity.ok(profileGetService.getProfile(userId));
    }

    // 프로필 정보 수정
    @PatchMapping
    public ResponseEntity<UserResDto> updateProfile(
            @RequestBody UserUpdateReqDto userUpdateReqDTO) {
        return ResponseEntity.ok(profileUpdateService.updateProfile(userUpdateReqDTO.getUserId(), userUpdateReqDTO));
    }

    // 여행 이미지 가져오기
    @GetMapping("/location/{userId}")
    public ResponseEntity<LocationResDto> getLocation(@PathVariable String userId) {
        return ResponseEntity.ok(getLocationService.getLocations(userId));
    }

    // 달력 불러오기
    @GetMapping("/calendar/{userId}/{year}/{month}")
    public ResponseEntity<CalendarResDto> getCalendar(@PathVariable String userId, @PathVariable int year, @PathVariable int month) {
        return ResponseEntity.ok(calendarService.getCalendar(userId, year, month));
    }
}
