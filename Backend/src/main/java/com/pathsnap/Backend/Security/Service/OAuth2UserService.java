package com.pathsnap.Backend.Security.Service;

import com.pathsnap.Backend.Security.Dto.Res.NaverRes;
import com.pathsnap.Backend.Security.Dto.Res.OAuth2Res;
import com.pathsnap.Backend.Security.Dto.Res.Oauth2UserRes;
import com.pathsnap.Backend.User.Entity.User1Entity;
import com.pathsnap.Backend.User.Repository.UserRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private static final String DEFAULT_ROLE = "ROLE USER";
    public OAuth2UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User.getAttributes());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Res oAuth2Res ;

        if (registrationId.equals("naver")) {
            oAuth2Res = new NaverRes(oAuth2User.getAttributes());
        }
        else {
            return null;
        }

        String userName = oAuth2Res.getProvider()+" "+oAuth2Res.getProviderId();
        User1Entity user = userRepository.findByUserName(userName);

        if (user == null) {
            user = createUser(userName, oAuth2Res);
        } else {
            updateUser(user, oAuth2Res);
        }

        return new Oauth2UserRes(oAuth2Res, user.getRole());
    }

    private User1Entity createUser(String userName, OAuth2Res oAuth2Res) {
        User1Entity user = new User1Entity();
        user.setUserId(UUID.randomUUID().toString());
        user.setUserName(userName);
        user.setEmail(oAuth2Res.getEmail());
        user.setRole(DEFAULT_ROLE);
        return userRepository.save(user);
    }

    private void updateUser(User1Entity user, OAuth2Res oAuth2Res) {
        user.setEmail(oAuth2Res.getEmail());
        userRepository.save(user);
    }
}
