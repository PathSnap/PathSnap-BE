package com.pathsnap.Backend.User.Dto.Res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class UserResDTO {
    private String name;
    private Date birthDate;
    private String phoneNumber;

}
