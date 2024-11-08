package com.pathsnap.Backend.User.Dto.Res;

import com.pathsnap.Backend.Image.Dto.Res.ImageResDto;
import com.pathsnap.Backend.S3.Dto.Res.S3ResDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserResDto {
    private String userName;
    private Date birthDate;
    private String phoneNumber;
    private List<ImageResDto> images;

}
