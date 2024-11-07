package com.pathsnap.Backend.S3.Component;

import com.pathsnap.Backend.Image.Entity.ImageEntity;
import com.pathsnap.Backend.Image.Repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ImageSave {

    private final ImageRepository imageRepository;

    public String exec(String url, String fileName) {
        ImageEntity newImage = new ImageEntity();
        String imageId = UUID.randomUUID().toString();
        newImage.setImageId(imageId);
        newImage.setUrl(url);
        newImage.setFileKey(fileName);
        imageRepository.save(newImage);

        return imageId;
    }
}
