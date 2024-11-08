package com.pathsnap.Backend.S3.Component.small;

import com.pathsnap.Backend.Image.Entity.ImageEntity;
import com.pathsnap.Backend.Image.Repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateImage {

    private final ImageRepository imageRepository;

    public String exec(String url, String fileName) {
        ImageEntity newImage = ImageEntity.builder()
                .imageId( UUID.randomUUID().toString())
                .url(url)
                .fileKey(fileName)
                .build();
        imageRepository.save(newImage);

        return newImage.getImageId();
    }
}
