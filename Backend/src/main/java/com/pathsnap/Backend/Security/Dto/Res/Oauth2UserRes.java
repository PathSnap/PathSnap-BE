package com.pathsnap.Backend.Security.Dto.Res;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;


public class Oauth2UserRes implements OAuth2User{
    private final OAuth2Res oAuth2Res;
    private final String role;

    public Oauth2UserRes(OAuth2Res oAuth2Res, String role) {
        this.oAuth2Res = oAuth2Res;
        this.role = role;
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

                return role;
            }
        });

        return collection;
    }

    @Override
    public String getName() {

        return oAuth2Res.getName();
    }

    public String getUserName() {

        return oAuth2Res.getProvider()+" "+oAuth2Res.getProviderId();
    }

    public String getEmail(){
        return oAuth2Res.getEmail();
    }
}
