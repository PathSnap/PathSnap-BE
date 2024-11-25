package com.pathsnap.Backend.Oauth2Login.Dto.Res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Oauth2UserResDto{
    private String role;
    private String userId;
    private String name;
    private String email;
}

