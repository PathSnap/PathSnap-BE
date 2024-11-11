package com.pathsnap.Backend.Security.Dto.Res;

import java.util.Map;

public class NaverRes implements OAuth2Res{

    private final Map<String,Object> attribute;

    public NaverRes(Map<String,Object> attribute){

        this.attribute =(Map<String,Object>)attribute.get("response");
    }

    @Override
    public String getProvider(){
        return "naver";
    }

    @Override
    public String getProviderId(){
        return (String) attribute.get("id");
    }

    @Override
    public String getName() {
        return (String) attribute.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attribute.get("email");
    }
}
