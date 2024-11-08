package com.pathsnap.Backend.S3.Component;

import com.pathsnap.Backend.S3.Component.small.CreateImage;
import com.pathsnap.Backend.S3.Component.small.CreateS3;
import com.pathsnap.Backend.S3.Dto.Res.S3ResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateS3Image {

    private final CreateS3 s3Upload;
    private final CreateImage imageSave;

    // 이미지를 S3에 업로드하고 정보를 저장하는 메서드
    public List<S3ResDto> exec(List<MultipartFile> images) {
        List<S3ResDto> s3ResDtoList = new ArrayList<>();

        images.forEach(image -> {
            // S3에 파일 업로드
            String url = s3Upload.exec(image);

            // 이미지 정보 저장
            String imageId = imageSave.exec(url, image.getOriginalFilename());

            // S3ResDto 객체 생성 후 리스트에 추가
            s3ResDtoList.add(new S3ResDto(imageId, url));
        });

        return s3ResDtoList;
    }
}
