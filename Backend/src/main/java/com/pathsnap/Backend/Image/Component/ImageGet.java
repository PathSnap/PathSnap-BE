package com.pathsnap.Backend.Image.Component;

import com.pathsnap.Backend.Image.Dto.Res.ImageResDto;
import com.pathsnap.Backend.Image.Entity.ImageEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ImageGet {

    public List<ImageResDto> exec(ImageEntity image) {
        if (image != null) {
            return List.of(new ImageResDto(image.getImageId(), image.getUrl())); // 이미지가 존재하면 S3ResDto로 변환
        }
        return Collections.emptyList(); // 이미지가 없으면 빈 리스트 반환
    }
}
