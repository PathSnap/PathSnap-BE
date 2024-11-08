package com.pathsnap.Backend.Image.Component;

import com.pathsnap.Backend.Exception.ImageNotFoundException;
import com.pathsnap.Backend.Image.Entity.Image1Entity;
import com.pathsnap.Backend.Image.Repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckImage {

    private final ImageRepository imageRepository;

    public Image1Entity exec(String imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new ImageNotFoundException(imageId));
    }
}
