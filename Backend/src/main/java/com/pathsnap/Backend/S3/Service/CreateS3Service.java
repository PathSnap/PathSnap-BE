package com.pathsnap.Backend.S3.Service;

import com.pathsnap.Backend.S3.Component.CreateImage;
import com.pathsnap.Backend.S3.Component.CreateS3;
import com.pathsnap.Backend.S3.Dto.Req.S3UploadReqDto;
import com.pathsnap.Backend.S3.Dto.Res.S3ListResDto;
import com.pathsnap.Backend.S3.Dto.Res.S3ResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateS3Service {

    private final CreateS3 s3Upload;
    private final CreateImage imageSave;

    public List<S3ListResDto> uploadImages(S3UploadReqDto imageReqDto) {

        List<S3ResDto> s3ResDtoList = new ArrayList<>();

        for (MultipartFile image : imageReqDto.getImages()) {
            // S3에 파일 업로드
            String url = s3Upload.exec(image);

            // 이미지 정보 저장
            String imageId = imageSave.exec(url, image.getOriginalFilename());

            // S3ResDto 객체 생성 후 리스트에 추가
            s3ResDtoList.add(new S3ResDto(imageId, url));
        }

        // S3ListResDto로 감싸서 반환
        List<S3ListResDto> response = new ArrayList<>();
        response.add(new S3ListResDto(s3ResDtoList));

        return response;
    }
}
