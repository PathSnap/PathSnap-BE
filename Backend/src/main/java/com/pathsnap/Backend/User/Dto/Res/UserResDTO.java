package com.pathsnap.Backend.User.Dto.Res;

import com.pathsnap.Backend.S3.Dto.Res.S3ResDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserResDTO {
    private String userName;
    private Date birthDate;
    private String phoneNumber;
    private List<S3ResDTO> images;

}
