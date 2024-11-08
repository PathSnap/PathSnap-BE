package com.pathsnap.Backend.ImagePhoto.Component;

import com.pathsnap.Backend.Exception.ImageNotFoundException;
import com.pathsnap.Backend.Image.Dto.Req.ImageReqDto;
import com.pathsnap.Backend.Image.Entity.Image1Entity;
import com.pathsnap.Backend.Image.Repository.ImageRepository;
import com.pathsnap.Backend.ImagePhoto.Entity.ImagePhoto1Entity;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecord1Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EditImagePhoto {
    private final ImageRepository imageRepository;

    public List<ImagePhoto1Entity> exec(PhotoRecord1Entity photoRecord, List<ImageReqDto> imageReqDtos) {
        List<ImagePhoto1Entity> updatedImagePhotos = new ArrayList<>();

        for (ImageReqDto imageReqDto : imageReqDtos) {
            Image1Entity image = imageRepository.findById(imageReqDto.getImageId())
                    .orElseThrow(() -> new ImageNotFoundException(imageReqDto.getImageId()));

            Optional<ImagePhoto1Entity> existingImagePhoto = photoRecord.getImagePhotos().stream()
                    .filter(imagePhoto -> imagePhoto.getImage().getImageId().equals(imageReqDto.getImageId()))
                    .findFirst();

            ImagePhoto1Entity imagePhoto = existingImagePhoto.orElseGet(() -> ImagePhoto1Entity.builder()
                    .imagePhotoId(UUID.randomUUID().toString())
                    .image(image)
                    .photoRecord(photoRecord)
                    .build());

            updatedImagePhotos.add(imagePhoto);
        }
        return updatedImagePhotos;
    }
}
