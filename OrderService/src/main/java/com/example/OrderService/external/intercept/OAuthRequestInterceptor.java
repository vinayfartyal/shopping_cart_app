package com.example.OrderService.external.intercept;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

//Interceptor is used to add authentication token to product service or payment service automatically
//whenever client send the request.
//We are overriding feign client configuration so that we are able to add authentication token to requested
//service using interceptors.
@Configuration
public class OAuthRequestInterceptor implements RequestInterceptor {

    @Autowired
    private OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", "Bearer "
                + oAuth2AuthorizedClientManager
                .authorize(OAuth2AuthorizeRequest
                        .withClientRegistrationId("internal-client")
                        .principal("internal")
                        .build())
                .getAccessToken().getTokenValue());
    }
}
