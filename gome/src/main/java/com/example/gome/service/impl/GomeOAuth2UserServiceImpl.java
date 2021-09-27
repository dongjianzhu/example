package com.example.gome.service.impl;

import cn.com.gome.cloud.openplatform.bridge.core.client.DefaultGmosClient;
import com.example.gome.constant.GomeConstant;
import com.example.gome.entity.GomeOAuth2User;
import com.example.gome.entity.mapper.UserConverter;
import com.example.gome.service.GomeOAuth2UserService;
import gome.open.api.sdk.cloud.client.domain.bridge.client.request.GomeShopShopInfoGetRequest;
import gome.open.api.sdk.cloud.client.domain.bridge.client.response.GomeShopShopInfoGetResponse;
import gome.open.api.sdk.cloud.client.domain.bridge.client.vo.ShopInfo;
import lombok.SneakyThrows;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

/**
 * @author: dongjianzhu
 * @create: 2021-09-24 10:26
 **/
@Service
public class GomeOAuth2UserServiceImpl implements GomeOAuth2UserService, OAuth2UserService<OAuth2UserRequest, OAuth2User> {


    /**
     * 从gome调取api获取shoInfo封装成GomeOAuth2User
     * @param userRequest
     * @return
     * @throws OAuth2AuthenticationException
     */
    @SneakyThrows
    @Override
    public GomeOAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2AccessToken accessToken = userRequest.getAccessToken();
        Instant issuedAt = accessToken.getIssuedAt();
        Instant expiresAt = accessToken.getExpiresAt();
        String token = accessToken.getTokenValue();
        DefaultGmosClient defaultGmosClient = new DefaultGmosClient(GomeConstant.SERVER_URI, GomeConstant.CLIENT_ID, GomeConstant.CLIENT_SECRET, token);
        GomeShopShopInfoGetRequest request = new GomeShopShopInfoGetRequest();
        GomeShopShopInfoGetResponse response = defaultGmosClient.execute(request);
        ShopInfo shopInfo = response.getResult();
        GomeOAuth2User gomeOAuth2User = UserConverter.INSTANCE.shopToUser(shopInfo);
        gomeOAuth2User.setToken(token);
        assert issuedAt != null;
        gomeOAuth2User.setIssuedAt(Date.from(issuedAt));
        assert expiresAt != null;
        gomeOAuth2User.setExpiresAt(Date.from(expiresAt));
        return gomeOAuth2User;
    }
}
