package com.pathsnap.Backend.S3.Controller;

import com.pathsnap.Backend.S3.Dto.Req.S3UploadReqDto;
import com.pathsnap.Backend.S3.Dto.Res.S3ListResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "S3 API", description = "S3에 관한 Controller")
public interface S3ControllerDocs {

    // S3 이미지 업로드
    @Operation(summary = "S3 이미지 업로드", description = "이미지 파일을 업로드하여 imageId와 URL을 반환", security = @SecurityRequirement(name = "AuthToken"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이미지 업로드 성공", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = S3ListResDto.class)
            )),
    })
    ResponseEntity<S3ListResDto> uploadImage(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "업로드할 이미지 파일 리스트", // 요청 본문 설명
                    required = true, // 필수 여부
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE, // 요청 본문 타입: multipart/form-data
                            schema = @Schema(implementation = S3UploadReqDto.class) // 요청 DTO 설명
                    )
            ) S3UploadReqDto imageReqDTO
    );

    // S3 이미지 삭제
    @Operation(summary = "S3 이미지 삭제", description = "S3 버킷에서 이미지와 관련된 데이터 삭제", security = @SecurityRequirement(name = "AuthToken"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이미지 삭제 성공", content = @Content),
    })
    ResponseEntity<Void> deleteImage(
            @Parameter(
                    name = "imageId",
                    description = "삭제하려는 이미지의 ID",
                    required = true // 필수 여부
            )
            @PathVariable String imageId // 경로 변수로 이미지 ID 전달
    );


}
