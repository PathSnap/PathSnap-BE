package com.pathsnap.Backend.User.Service;

import com.pathsnap.Backend.Exception.ImageNotFoundException;
import com.pathsnap.Backend.Exception.UserNotFoundException;
import com.pathsnap.Backend.Image.Entity.ImageEntity;
import com.pathsnap.Backend.Image.Repository.ImageRepository;
import com.pathsnap.Backend.Record.Dto.Res.LocationResDTO;
import com.pathsnap.Backend.Record.Entity.RecordEntity;
import com.pathsnap.Backend.Record.Repository.RecordRepository;
import com.pathsnap.Backend.S3.Dto.Res.S3ResDTO;
import com.pathsnap.Backend.User.Dto.Req.UserUpdateReqDTO;
import com.pathsnap.Backend.User.Dto.Res.UserResDTO;
import com.pathsnap.Backend.User.Entity.UserEntity;
import com.pathsnap.Backend.User.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private RecordRepository recordRepository;

    public UserResDTO getProfile(String userId) {

        // 사용자 찾기 및 예외 처리
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        // 사용자 이미지
        List<S3ResDTO> images = Collections.emptyList();

        if (user.getImage() != null) {
            images = List.of(new S3ResDTO(user.getImage().getImageId(), user.getImage().getUrl()));
        }

        // UserResDTO 생성 및 반환
        return new UserResDTO(
                user.getUserName(),
                user.getBirthDate(),
                user.getPhoneNumber(),
                images
        );
    }

    public UserResDTO updateProfile(String userId, UserUpdateReqDTO userUpdateReqDTO) {

        // 사용자 찾기 및 예외 처리
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        // 필요한 필드만 업데이트
        if (userUpdateReqDTO.getUserName() != null) {
            user.setUserName(userUpdateReqDTO.getUserName());
        }
        if (userUpdateReqDTO.getBirthDate() != null) {
            user.setBirthDate(userUpdateReqDTO.getBirthDate());
        }
        if (userUpdateReqDTO.getPhoneNumber() != null) {
            user.setPhoneNumber(userUpdateReqDTO.getPhoneNumber());
        }
        if (userUpdateReqDTO.getImageId() != null) {
            ImageEntity image = imageRepository.findById(userUpdateReqDTO.getImageId())
                    .orElseThrow(() -> new ImageNotFoundException(userUpdateReqDTO.getImageId()));
            user.setImage(image);
        }

        // 변경된 엔티티 저장
        userRepository.save(user);

        // 사용자 이미지
        List<S3ResDTO> images = Collections.emptyList();
        if (user.getImage() != null) {
            images = List.of(new S3ResDTO(user.getImage().getImageId(), user.getImage().getUrl()));
        }

        // 업데이트된 정보를 UserResDTO로 변환하여 반환
        return new UserResDTO(
                user.getUserName(),
                user.getBirthDate(),
                user.getPhoneNumber(),
                images

        );
    }



    public LocationResDTO getLocations(String userId) {
        // 사용자 존재 여부 확인 및 예외 처리
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        // UserEntity로 레코드 찾기
        List<RecordEntity> records = recordRepository.findByUser_UserId(userId);


        List<LocationResDTO.LocationDTO> locationDTOs = records.stream()
                .map(record -> new LocationResDTO.LocationDTO(record.getRecordId(), record.getRecordName()))
                .collect(Collectors.toList());

        return new LocationResDTO(locationDTOs);

    }
}
