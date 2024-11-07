package com.pathsnap.Backend.User.Dto.Req;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserUpdateReqDto {
    private String userName;
    private Date birthDate;
    private String phoneNumber;
    private String imageId;

}
