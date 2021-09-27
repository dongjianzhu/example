package com.example.gome.config;

import com.example.gome.constant.GomeConstant;
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
        return ClientRegistration.withRegistrationId("gome")
                .clientId(GomeConstant.CLIENT_ID)
                .clientSecret(GomeConstant.CLIENT_SECRET)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri(GomeConstant.REDIRECT_URI)
                .scope("profile", "email", "address", "phone")
                .authorizationUri(GomeConstant.AUTHORIZATION_URI)
                .tokenUri(GomeConstant.TOKEN_URI)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
//                .userInfoUri("https://oauth.gome.com.cn/user")
                .userNameAttributeName(IdTokenClaimNames.SUB)
//                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
                .clientName("gome")
                .build();
    }
}
