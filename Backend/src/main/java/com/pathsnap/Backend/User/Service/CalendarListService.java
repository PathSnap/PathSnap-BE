package com.pathsnap.Backend.User.Service;

import com.pathsnap.Backend.Exception.UserNotFoundException;
import com.pathsnap.Backend.Image.Dto.Res.ImageListResDTO;
import com.pathsnap.Backend.Image.Dto.Res.ImageResDTO;
import com.pathsnap.Backend.Image.Entity.ImageEntity;
import com.pathsnap.Backend.ImagePhoto.Entity.ImagePhotoEntity;
import com.pathsnap.Backend.ImagePhoto.Repository.ImagePhotoRepository;
import com.pathsnap.Backend.PackTrip.Dto.Res.PackTripResDTO;
import com.pathsnap.Backend.PhotoRecord.Entity.PhotoRecordEntity;
import com.pathsnap.Backend.PhotoRecord.Repository.PhotoRecordRepository;
import com.pathsnap.Backend.User.Dto.Res.CalendarResDTO;
import com.pathsnap.Backend.Record.Entity.RecordEntity;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import com.pathsnap.Backend.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarListService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private PhotoRecordRepository photoRecordRepository;

    @Autowired
    private ImagePhotoRepository imagePhotoRepository;

    private final CalendarPackTripService calendarPackTripService;

    public CalendarResDTO getCalendar(String userId, Integer month) {

        // 사용자 존재 여부 확인 및 예외 처리
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        // UserEntity로 레코드 찾기
        List<RecordEntity> records = recordRepository.findByUser_UserId(userId);

        // Date 기준으로 오름차순 정렬
        records.sort(Comparator.comparing(RecordEntity::getStartDate));

        List<CalendarResDTO.CalendarDTO> calendarDTOs = new ArrayList<>();

        // recordId로 photo 찾기
        for (RecordEntity record : records) {
            List<PhotoRecordEntity> photos = photoRecordRepository.findByRecord_RecordId(record.getRecordId());
            ImageListResDTO imageListResDTO = new ImageListResDTO(new ArrayList<>());

            if (!photos.isEmpty()) {

                // PhotoRecord의 ID 가져오기
                String photoRecordId = photos.get(0).getPhotoRecordId();

                List<ImagePhotoEntity> imagePhotos = imagePhotoRepository.findByPhotoRecord_PhotoRecordId(photoRecordId);

                if (!imagePhotos.isEmpty()) {
                    // 첫 번째 이미지만 추가
                    ImagePhotoEntity firstImagePhoto = imagePhotos.get(0);
                    ImageEntity firstImage = firstImagePhoto.getImage();

                    imageListResDTO.getImages().add(new ImageResDTO(firstImage.getImageId(), firstImage.getUrl()));
                }
            }

            // calendarDTO에 추가
            calendarDTOs.add(new CalendarResDTO.CalendarDTO(record.getRecordId(),record.getStartDate() ,record.getRecordName(), imageListResDTO));
        }


        // 사용자의 PackTrip을 가져오는 메서드
        List<PackTripResDTO> newTrips = calendarPackTripService.getPackTrips(userId, month);

        // 최종 결과 반환
        return new CalendarResDTO(calendarDTOs, newTrips);


    }
}
