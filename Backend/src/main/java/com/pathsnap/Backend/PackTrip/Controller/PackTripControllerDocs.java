package com.pathsnap.Backend.PackTrip.Controller;

import com.pathsnap.Backend.PackTrip.Dto.Req.PackTripReqDto;
import com.pathsnap.Backend.PackTrip.Dto.Res.PackTripResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "여행 묶음 API", description = "달력에서 여행 일정 그룹화에 관한 controller")
public interface PackTripControllerDocs {

    @Operation(summary = "여행 묶음 생성", description = "그룹화할 여행 정보들을 전달", security = @SecurityRequirement(name = "AuthToken"))
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "여행 그룹화 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PackTripResDto.class)
                    )
            ),
    })
    @PostMapping("/profiles/trips")
    ResponseEntity<PackTripResDto> createPackTrip(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "제목과 그룹화할 날짜 요청",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = PackTripReqDto.class)
                    )
            ) PackTripReqDto packTripReqDTO
    );
}
