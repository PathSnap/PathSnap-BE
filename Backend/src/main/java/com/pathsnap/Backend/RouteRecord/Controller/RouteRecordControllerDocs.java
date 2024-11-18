package com.pathsnap.Backend.RouteRecord.Controller;

import com.pathsnap.Backend.Record.Dto.Req.RecordEditReqDto;
import com.pathsnap.Backend.Record.Dto.Res.RecordEditResDto;
import com.pathsnap.Backend.Record.Dto.Res.RecordStartDTO;
import com.pathsnap.Backend.Record.Dto.Res.RecordUpdateResDto;
import com.pathsnap.Backend.RouteRecord.Dto.Req.RouteReqDto;
import com.pathsnap.Backend.RouteRecord.Dto.Res.RouteRecordStartDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface RouteRecordControllerDocs {

    //경로 기록 시작 api
    @Parameters(value = {
            @Parameter(name = "recordId", description = "경로 기록을 시작하려는 기록 아이디"),
            @Parameter(name = "seq", description = "경로 기록 순서")
    })
    @Operation(summary = "경로 기록 시작 저장", description = " 기록 아이디와 경로 기록 순서를 받아 저장")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "경로 기록 완료", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RouteRecordStartDto.class)))
    })
    ResponseEntity<RouteRecordStartDto> RouteRecordStart(String recordId, int seq);



        //경로 저장 api
    @Operation(summary = "경로 기록 저장", description = " 경로 기록 아이디와 경로와 관련된 정보들을 받아 저장")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "경로 기록 완료", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RouteRecordStartDto.class)))
    })
    ResponseEntity<RouteRecordStartDto> editRoute(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "경로 기록의 경로 정보를 요청",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = RouteReqDto.class)
                    )
            ) RouteReqDto request
    );
}