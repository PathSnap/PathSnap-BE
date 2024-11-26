package com.pathsnap.Backend.User.Controller;

import com.pathsnap.Backend.User.Dto.Res.CalendarResDto;
import com.pathsnap.Backend.User.Dto.Res.LocationResDto;
import com.pathsnap.Backend.User.Dto.Req.UserUpdateReqDto;
import com.pathsnap.Backend.User.Dto.Res.UserResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "사용자 API", description = "사용자 정보 및 달력 관련 Controller")
public interface UserControllerDocs {

    // 프로필 정보 불러오기
    @Operation(summary = "사용자 프로필 정보 조회", description = "사용자의 프로필 정보를 조회", security = @SecurityRequirement(name = "AuthToken"))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "사용자 프로필 정보 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResDto.class)
                    )
            )
    })
    @GetMapping("/{userId}")
    ResponseEntity<UserResDto> getProfile(
            @Parameter(description = "조회할 사용자 ID", required = true)
            @PathVariable String userId
    );

    // 프로필 정보 수정
    @Operation(summary = "사용자 프로필 정보 수정", description = "사용자의 프로필 정보를 수정", security = @SecurityRequirement(name = "AuthToken"))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "사용자 프로필 정보 수정 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResDto.class)
                    )
            )
    })
    @PatchMapping
    ResponseEntity<UserResDto> updateProfile(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "사용자 프로필 정보 수정 요청 데이터",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = UserUpdateReqDto.class)
                    )
            ) UserUpdateReqDto userUpdateReqDTO
    );

    // 여행 이미지 가져오기
    @Operation(summary = "사용자 여행 이미지 조회", description = "사용자의 여행 이미지를 조회", security = @SecurityRequirement(name = "AuthToken"))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "여행 이미지 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LocationResDto.class)
                    )
            )
    })
    @GetMapping("/location/{userId}")
    ResponseEntity<LocationResDto> getLocation(
            @Parameter(description = "사용자 ID", required = true)
            @PathVariable String userId
    );

    // 달력 불러오기
    @Operation(summary = "사용자 달력 조회", description = "사용자의 달력을 조회", security = @SecurityRequirement(name = "AuthToken"))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "달력 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CalendarResDto.class)
                    )
            )
    })
    @GetMapping("/calendar/{userId}/{month}")
    ResponseEntity<CalendarResDto> getCalendar(
            @Parameter(description = "사용자 ID", required = true)
            @PathVariable String userId,
            @Parameter(description = "조회할 월", required = true)
            @PathVariable int month
    );
}
