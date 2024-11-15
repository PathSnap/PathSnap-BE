package com.pathsnap.Backend.Oauth2Login.Service;

import com.pathsnap.Backend.Oauth2Login.Dto.Res.CustomOauth2UserResDto;
import com.pathsnap.Backend.Oauth2Login.Dto.Res.NaverRes;
import com.pathsnap.Backend.Oauth2Login.Dto.Res.OAuth2Res;
import com.pathsnap.Backend.Oauth2Login.Dto.Res.Oauth2UserResDto;
import com.pathsnap.Backend.User.Entity.User1Entity;
import com.pathsnap.Backend.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    //DefaultOauth2UserService를 상속받아 loadUser 메소드를 재정의하여 Oauth2 로그인 로직을 커스터마이징
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Res oAuth2Res;

        //naver 로그인일 경우
        if (registrationId.equals("naver")) {
            oAuth2Res = new NaverRes(oAuth2User.getAttributes());
            System.out.println("oAuth2res는" + oAuth2Res);
        }
        //kakao 로그인일 경우
        else {
            return null;
        }

        //provider(네이버)와 providerId(고유 사용자 ID)를 조합해 고유ID설정
        String NaverUserId= oAuth2Res.getProvider() + " " + oAuth2Res.getProviderId();
        System.out.println("username은 " + NaverUserId);

        Optional<User1Entity> existData = userRepository.findById(NaverUserId);
        //회원정보가 존재하지 않을경우 신규회원 저장
        if (!existData.isPresent()) {
            User1Entity userEntity = new User1Entity();
            userEntity.setUserId(NaverUserId);
            userEntity.setEmail(oAuth2Res.getEmail());
            userEntity.setName(oAuth2Res.getName());
            userEntity.setRole("ROLE_USER");

            userRepository.save(userEntity);
            //dto로 정보를 담아 반환
            Oauth2UserResDto oauth2UserResDto = new Oauth2UserResDto();
            oauth2UserResDto.setUserId(NaverUserId);
            oauth2UserResDto.setEmail(oAuth2Res.getEmail());
            oauth2UserResDto.setName(oAuth2Res.getName());
            oauth2UserResDto.setRole("ROLE_USER");

            return new CustomOauth2UserResDto(oauth2UserResDto);
        }
        //회원정보가 존재할 경우 이메일과 이름만 다시 갱신하여 저장
        else {
            User1Entity user1Entity = existData.get();
            user1Entity.setEmail(oAuth2Res.getEmail());
            user1Entity.setName(oAuth2Res.getName());

            userRepository.save(user1Entity);
            //dto로 정보를 담아 반환
            Oauth2UserResDto oauth2UserResDto = new Oauth2UserResDto();
            oauth2UserResDto.setUserId(user1Entity.getUserId());
            oauth2UserResDto.setEmail(user1Entity.getEmail());
            oauth2UserResDto.setName(oAuth2Res.getName());

            return new CustomOauth2UserResDto(oauth2UserResDto);
        }
    }
}
