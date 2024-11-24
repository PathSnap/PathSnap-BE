package com.pathsnap.Backend.Oauth2Login.Service;

import com.pathsnap.Backend.Oauth2Login.Repository.RefreshRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshService {

    private final RefreshRepository refreshRepository;
    @Transactional
    public void deleteRefreshToken(String refresh) {
        refreshRepository.deleteByRefresh(refresh);
    }

    @Transactional
    public boolean existsByRefresh(String refresh){
        return refreshRepository.existsByRefresh(refresh);
    }
}
