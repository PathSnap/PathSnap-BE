package com.pathsnap.Backend.PhotoRecord.Controller;

import com.pathsnap.Backend.PhotoRecord.Dto.Req.PhotoRecordCreateReqDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Req.PhotoRecordEditReqDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Req.PhotoUpdateReqDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoLocationResDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoRecordResDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoSummaryResDto;
import com.pathsnap.Backend.PhotoRecord.Dto.Res.PhotoUpdateResDto;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecord1Entity;
import com.pathsnap.Backend.Record.Dto.Req.RecordEditReqDto;
import com.pathsnap.Backend.Record.Dto.Req.RecordUpdateReqDto;
import com.pathsnap.Backend.Record.Dto.Res.RecordDetailResDto;
import com.pathsnap.Backend.Record.Dto.Res.RecordEditResDto;
import com.pathsnap.Backend.Record.Dto.Res.RecordStartDTO;
import com.pathsnap.Backend.Record.Dto.Res.RecordUpdateResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Tag(name = "포토 기록 API", description = "포토 기록에 관한 Controller")
public interface PhotoRecordControllerDocs {

        //PhotoRecord 생성 api
        @Parameter(name = "recordId", description = "포토 기록을 생성하려는 기록 아이디")
        @Operation(summary = "포토 기록 시작 생성", description = "기록 아이디를 받아 포토 기록을 생성한 후 전달", security = @SecurityRequirement(name = "AuthToken"))
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "포토 기록 생성 완료", content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = PhotoRecordResDto.class)))
        })
        ResponseEntity<PhotoRecordResDto> addPhoto(
                @PathVariable String recordId,
                @io.swagger.v3.oas.annotations.parameters.RequestBody(
                        description = "생성하려는 포토 기록 정보를 요청",
                        required = true,
                        content = @Content(
                                schema = @Schema(implementation = PhotoRecordCreateReqDto.class)
                        )
                ) PhotoRecordCreateReqDto request
        );

        //PhotoRecord 수정 api
        @Operation(summary = "포토 기록 수정", description = "수정된 포토 기록을 받아 저장하고 수정된 내용을 반환합니다.", security = @SecurityRequirement(name = "AuthToken"))
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "포토 기록 수정 완료", content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = PhotoRecordResDto.class)))
        })
        ResponseEntity<PhotoRecordResDto> editPhoto(
                @io.swagger.v3.oas.annotations.parameters.RequestBody(
                        description = "변경된 포토 기록의 정보를 요청",
                        required = true,
                        content = @Content(
                                schema = @Schema(implementation = PhotoRecordEditReqDto.class)
                        )
                ) PhotoRecordEditReqDto request
        );

        //PhotoRecord 조회 api
        @Parameter(name = "photoId", description = "포토 기록의 아이디")
        @Operation(summary = "포토 기록 요약 조회", description = "포토 기록의 아이디를 받아 해당 포토 기록의 요약 정보를 반환합니다.", security = @SecurityRequirement(name = "AuthToken"))
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "포토 기록 요약 조회 완료", content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = PhotoSummaryResDto.class)))
        })
        ResponseEntity<PhotoSummaryResDto> getSummaryPhoto(String photoId);

        //내 주변 PhotoRecord 조회 api
        @Parameters(value = {
                @Parameter(name = "userId", description = "주변을 조회하려는 유저의 아이디"),
                @Parameter(name = "lon", description = "유저가 화면에서 보고있는 경도"),
                @Parameter(name = "lat", description = "유저가 화면에서 보고있는 위도"),
                @Parameter(name = "radius", description = "화면에서 유저에게 보여주려는 범위")
        })
        @Operation(summary = "유저가 화면에서 보고있는 포토 기록 조회", description = "피라미터로 받은 값들로 유저가 화면에서 보고있는 위치에서 주변 포토 기록들을 전달", security = @SecurityRequirement(name = "AuthToken"))
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "주변 포토 기록 조회 완료", content = @Content (
                        mediaType = "application/json",
                        schema = @Schema(implementation = PhotoLocationResDto.class)))
        })
        ResponseEntity<List<PhotoLocationResDto>> getPhotosWithinRadius(String userId, double lon,double lat, double radius);

        //Record 삭제 api
        @Parameter(name = "photoId", description = "유저가 삭제하려는 포토 아이디" )
        @Operation(summary = "포토 기록 삭제", description = "피라미터로 받은 포토 기록을 삭제", security = @SecurityRequirement(name = "AuthToken"))
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "포토 기록 삭제 완료", content = @Content (
                        mediaType = "application/json"
                ))
        })
        ResponseEntity<Void> deletePhoto(String photoId);
}
