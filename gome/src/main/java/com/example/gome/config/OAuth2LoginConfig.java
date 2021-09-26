package com.example.gome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;

/**
 * @author: dongjianzhu
 * @create: 2021-09-26 12:32
 **/
@Configuration
public class OAuth2LoginConfig {

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(this.gomeClientRegistration());
    }

    private ClientRegistration gomeClientRegistration() {
        return ClientRegistration.withRegistrationId("google")
                .clientId("c52e799904a84598ad23b5b91f46184f")
                .clientSecret("474a73f61b74483982eea7864a24e2f4")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/gome")
                .scope("openid", "profile", "email", "address", "phone")
                .authorizationUri("http://oauth.gome.com.cn/authorize")
                .tokenUri("http://oauth.gome.com.cn/token")
//                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                .userNameAttributeName(IdTokenClaimNames.SUB)
//                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
                .clientName("gome")
                .build();
    }
}
