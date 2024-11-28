package com.pathsnap.Backend.Oauth2Login.Dto.Res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReissueResDto {
    private String accessToken;
    private String refreshToken;
}
