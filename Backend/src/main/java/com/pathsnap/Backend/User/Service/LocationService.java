package com.pathsnap.Backend.User.Service;

import com.pathsnap.Backend.Exception.UserNotFoundException;
import com.pathsnap.Backend.Image.Dto.Res.ImageListResDTO;
import com.pathsnap.Backend.Image.Dto.Res.ImageResDTO;
import com.pathsnap.Backend.Image.Entity.ImageEntity;
import com.pathsnap.Backend.ImagePhoto.Entity.ImagePhotoEntity;
import com.pathsnap.Backend.ImagePhoto.Repository.ImagePhotoRepository;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecordEntity;
import com.pathsnap.Backend.PhotoRecord.Repository.PhotoRecordRepository;
import com.pathsnap.Backend.User.Dto.Res.LocationResDTO;
import com.pathsnap.Backend.Record.Entity.RecordEntity;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import com.pathsnap.Backend.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class LocationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private PhotoRecordRepository photoRecordRepository;

    @Autowired
    private ImagePhotoRepository imagePhotoRepository;

    // 여행 이미지 불러오기
    public LocationResDTO getLocations(String userId) {

        // 사용자 존재 여부 확인 및 예외 처리
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        // UserEntity로 레코드 찾기
        List<RecordEntity> records = recordRepository.findByUser_UserId(userId);

        // Date 기준으로 오름차순 정렬
        records.sort(Comparator.comparing(RecordEntity::getStartDate));

        List<LocationResDTO.LocationDTO> locationDTOs = new ArrayList<>();

        // recordId로 photo 찾기
        for (RecordEntity record : records) {
            List<PhotoRecordEntity> photos = photoRecordRepository.findByRecord_RecordId(record.getRecordId());
            ImageListResDTO imageListResDTO = new ImageListResDTO(new ArrayList<>());

            if (!photos.isEmpty()) {
                
                // seq가 가장 작은 PhotoRecord의 ID 가져오기
                String photoRecordId = photos.get(0).getPhotoRecordId();

                List<ImagePhotoEntity> imagePhotos = imagePhotoRepository.findByPhotoRecord_PhotoRecordId(photoRecordId);

                if (!imagePhotos.isEmpty()) {
                    // 첫 번째 이미지만 추가
                    ImagePhotoEntity firstImagePhoto = imagePhotos.get(0);
                    ImageEntity firstImage = firstImagePhoto.getImage();

                    imageListResDTO.getImages().add(new ImageResDTO(firstImage.getImageId(), firstImage.getUrl()));
                }
            }

            // LocationDTO에 추가
            locationDTOs.add(new LocationResDTO.LocationDTO(record.getRecordId(), record.getRecordName(), imageListResDTO));
        }

        return new LocationResDTO(locationDTOs);
    }
}
