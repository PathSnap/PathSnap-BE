package com.pathsnap.Backend.Security.Dto.Res;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


public class CustomOauth2UserResDto implements OAuth2User{
    private Oauth2UserResDto oauth2UserResDto;

    public CustomOauth2UserResDto(Oauth2UserResDto oauth2UserResDto) {

        this.oauth2UserResDto = oauth2UserResDto;
    }

    @Override
    public Map<String, Object> getAttributes() {

        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {

                return oauth2UserResDto.getRole();
            }
        });

        return collection;
    }


    @Override
    public String getName() {

        return oauth2UserResDto.getName(); // 빈 문자열 반환
    }

    public String getEmail(){
        return oauth2UserResDto.getEmail();

    }
    public String getUserName() {

        return oauth2UserResDto.getUserName();
    }

}
