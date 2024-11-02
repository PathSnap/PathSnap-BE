package com.pathsnap.Backend.User.Dto.Req;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserUpdateReqDTO {
    private String userName;
    private Date birthDate;
    private String phoneNumber;

}
