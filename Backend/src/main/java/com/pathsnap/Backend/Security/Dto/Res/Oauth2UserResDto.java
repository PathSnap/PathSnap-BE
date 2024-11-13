package com.pathsnap.Backend.Security.Dto.Res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Oauth2UserResDto{
    private String role;
    private String userId;
    private String name;
    private String email;
    private String userName;

}

