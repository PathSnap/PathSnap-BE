package com.pathsnap.Backend.User.Dto.Req;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserUpdateReqDto {
    private String userId;
    private String userName;
    private LocalDate birthDate;
    private String phoneNumber;
    private String address;
    private String imageId;

}
