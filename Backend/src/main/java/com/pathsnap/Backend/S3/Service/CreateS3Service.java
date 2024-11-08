package com.pathsnap.Backend.S3.Service;

import com.pathsnap.Backend.S3.Component.CreateS3Image;
import com.pathsnap.Backend.S3.Dto.Req.S3UploadReqDto;
import com.pathsnap.Backend.S3.Dto.Res.S3ListResDto;
import com.pathsnap.Backend.S3.Dto.Res.S3ResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateS3Service {

    private final CreateS3Image createS3Image;

    public List<S3ListResDto> uploadImages(S3UploadReqDto imageReqDto) {

        // createS3Image를 통해 이미지 업로드 및 정보 저장
        List<S3ResDto> s3ResDtoList = createS3Image.exec(imageReqDto.getImages());

        // S3ListResDto로 감싸서 반환
        List<S3ListResDto> response = new ArrayList<>();
        response.add(new S3ListResDto(s3ResDtoList));

        return response;
    }
}
