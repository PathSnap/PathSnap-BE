package com.pathsnap.Backend.Record.Controller;

import com.pathsnap.Backend.Record.Dto.Res.RecordStartDTO;
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
    public ResponseEntity<RecordStartDTO> startRecord(String userId, boolean recordIsGroup);
    }
