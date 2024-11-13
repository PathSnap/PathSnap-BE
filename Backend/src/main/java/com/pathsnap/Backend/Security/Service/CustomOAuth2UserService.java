package com.pathsnap.Backend.Security.Service;

import com.pathsnap.Backend.Security.Dto.Res.CustomOauth2UserResDto;
import com.pathsnap.Backend.Security.Dto.Res.NaverRes;
import com.pathsnap.Backend.Security.Dto.Res.OAuth2Res;
import com.pathsnap.Backend.Security.Dto.Res.Oauth2UserResDto;
import com.pathsnap.Backend.User.Entity.User1Entity;
import com.pathsnap.Backend.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private static final String DEFAULT_ROLE = "ROLE_USER";

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Res oAuth2Res;

        if (registrationId.equals("naver")) {
            oAuth2Res = new NaverRes(oAuth2User.getAttributes());
            System.out.println("oAuth2res는" + oAuth2Res);
        } else {
            return null;
        }

        String username = oAuth2Res.getProvider() + " " + oAuth2Res.getProviderId();
        System.out.println("username은 " + username);
        User1Entity existData = userRepository.findByUserName(username);

        if (existData == null) {
            String userId = UUID.randomUUID().toString();
            User1Entity userEntity = new User1Entity();
            userEntity.setUserId(userId);  // UUID 설정
            userEntity.setUserName(username);
            userEntity.setEmail(oAuth2Res.getEmail());
            userEntity.setRole("ROLE_USER");

            userRepository.save(userEntity);

            Oauth2UserResDto oauth2UserResDto = new Oauth2UserResDto();
            oauth2UserResDto.setUserName(username);
            oauth2UserResDto.setName(oAuth2Res.getName());
            oauth2UserResDto.setRole("ROLE_USER");

            return new CustomOauth2UserResDto(oauth2UserResDto);
        }
        else {
            existData.setEmail(oAuth2Res.getEmail());
            existData.setName(oAuth2Res.getName());

            userRepository.save(existData);

            Oauth2UserResDto oauth2UserResDto = new Oauth2UserResDto();
            oauth2UserResDto.setUserName(existData.getUserName());
            oauth2UserResDto.setName(oAuth2Res.getName());
            oauth2UserResDto.setRole(existData.getRole());

            return new CustomOauth2UserResDto(oauth2UserResDto);
        }
    }
}
