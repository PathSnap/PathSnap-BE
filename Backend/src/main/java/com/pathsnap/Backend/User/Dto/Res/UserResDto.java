package com.pathsnap.Backend.User.Dto.Res;

import com.pathsnap.Backend.Image.Dto.Res.ImageResDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserResDto {
    private String userName;
    private LocalDate birthDate;
    private String phoneNumber;
    private String address;
    private Double lat;
    private Double lng;
    private List<ImageResDto> images;

}
