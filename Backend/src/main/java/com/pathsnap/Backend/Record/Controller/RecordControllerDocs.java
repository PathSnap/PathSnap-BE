package com.pathsnap.Backend.Record.Controller;

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
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "기록 API", description = "기록에 관한 Controller")
public interface RecordControllerDocs {

    //Record 생성 api
    @Parameters(value = {
            @Parameter(name = "userId", description = "기록을 생성하려는 유저의 아이디"),
            @Parameter(name = "recordIsGroup", description = "개인 기록인지 그룹 기록인지 구분(개인 기록 = false, 그룹 기록 = true)")
    })
    @Operation(summary = "기록 시작 생성", description = "유저 아이디와 기록 구분을 받아 유저의 기록을 생성한다음 기록ID를 전달")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "기록 생성 완료", content = @Content (
                    mediaType = "application/json",
                    schema = @Schema(implementation = RecordStartDTO.class)))
    })
    ResponseEntity<RecordStartDTO> startRecord(String userId, boolean recordIsGroup);

    //Record 조회 api
    @Parameter(name = "recordId", description = "유저의 기록아이디" )
    @Operation(summary = "기록 상세 조회", description = "피라미터로 받은 기록으로 기록 관련 정보들을 전달")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "기록 조회 완료", content = @Content (
                    mediaType = "application/json",
                    schema = @Schema(implementation = RecordStartDTO.class)))
    })
    ResponseEntity<RecordDetailResDto> getRecordDetails(String recordId);

    //Record 순서 변경 api
    @Operation(summary = "기록 순서 변경", description = "포토 기록과 경로 기록의 순서를 변경하고 변경된 정보들을 전달")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "기록 순서 변경 완료", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RecordUpdateResDto.class)))
    })
    ResponseEntity<RecordUpdateResDto> updateRecordDetails(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "순서 변경된 포토기록과 경로기록의 정보를 요청",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = RecordUpdateReqDto.class)
                    )
            ) RecordUpdateReqDto request
    );

    //Record 수정 api
    @Operation(summary = "기록 수정", description = "수정된 기록을 받아 저장하고 전달")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "기록 수정 완료", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RecordUpdateResDto.class)))
    })
    ResponseEntity<RecordEditResDto> editRecordName(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "변경된 기록의 정보를 요청",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = RecordEditReqDto.class)
                    )
            ) RecordEditReqDto request
    );

    //Record 삭제 api
    @Parameter(name = "recordId", description = "유저의 기록아이디" )
    @Operation(summary = "기록 삭제", description = "피라미터로 받은 기록을 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "기록 삭제 완료", content = @Content (
                    mediaType = "application/json",
                    schema = @Schema(implementation = RecordStartDTO.class)))
    })
    ResponseEntity<Void> deleteRecord(String recordId);
}
