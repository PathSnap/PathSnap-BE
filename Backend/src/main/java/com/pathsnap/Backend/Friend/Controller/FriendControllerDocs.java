package com.pathsnap.Backend.Friend.Controller;


import com.pathsnap.Backend.Friend.Dto.Res.FriendAddResDto;
import com.pathsnap.Backend.Friend.Dto.Res.FriendListResDto;
import com.pathsnap.Backend.Friend.Dto.Res.FriendSearchResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "친구 API", description = "친구에 관한 Controller")
public interface FriendControllerDocs {

    //기록에 속한 유저 조회 api
    @Operation(summary = "기록에 속한 유저 조회", description = "기록을 생성한 유저와 해당 기록에 포함된 유저를 조회")
    @Parameter(name = "recordId", description = "조회하려는 기록 아이디")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 조회 완료(user = 팀장, friend = 팀원)", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = FriendListResDto.class)))
    })
    ResponseEntity<FriendListResDto> getFriendRecord(String recordId);


    //유저 추가 api
    @Operation(summary = "기록에 유저 추가", description = "기록 아이디와 유저 아이디를 받아 기록에 유저를 추가")
    @Parameters(value = {
            @Parameter(name = "recordId", description = "유저를 추가하려는 기록 아이디"),
            @Parameter(name = "userId", description = "기록에 추가하려는 유저")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 추가 완료", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = FriendListResDto.class)))
    })
    ResponseEntity<FriendAddResDto> addFriendToRecord(String userId, String recordId);

    //유저 검색 api
    @Operation(summary = "유저 검색", description = "기록에 추가하려는 유저를 이름으로 검색 ")
    @Parameter(name = "name", description = "검색하려는 유저 이름")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "유저 검색 완료", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = FriendSearchResDto.class)))
    })
    ResponseEntity<List<FriendSearchResDto>> searchFriends(String name);

    //유저 삭제 api
    @Parameter(name = "friendId", description = "삭제하려는 친구 아이디" )
    @Operation(summary = "친구 삭제", description = "피라미터로 받은 친구를 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "친구 삭제 완료", content = @Content (
                    mediaType = "application/json"
            ))
    })
    ResponseEntity<Void> deleteFriend(String friendId);
}
