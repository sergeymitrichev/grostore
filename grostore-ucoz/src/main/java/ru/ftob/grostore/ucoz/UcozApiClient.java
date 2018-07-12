package ru.ftob.grostore.ucoz;

import com.github.scribejava.apis.UcozApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth10aService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class UcozApiClient {

    private final String SITE_URL;
    private final OAuth10aService service;
    private final OAuth1AccessToken accessToken;

    public UcozApiClient(
            String apiKey,
            String apiSecret,
            String token,
            String tokenSecret,
            String siteUrl
                         ) throws InterruptedException, ExecutionException, IOException {
        service = new ServiceBuilder(apiKey)
                .apiSecret(apiSecret)
                .callback(siteUrl)
                .debug()
                .build(UcozApi.instance());
        SITE_URL = siteUrl;
        OAuth1RequestToken requestToken = service.getRequestToken();
        accessToken = new OAuth1AccessToken(token, tokenSecret);
//        String url = service.getAuthorizationUrl(requestToken);
//        System.out.println("My URL:");
//        System.out.println(url);
//        final String oauthVerifier = service.getAuthorizationUrl(requestToken).split("oauth_token=")[1];
//        service.getAccessToken(requestToken, oauthVerifier);
//        this.accessToken = service.getAccessToken(requestToken, oauthVerifier);
    }

    public Response makeRequest(String path, Verb method, Map<String,String> parameters) {
        try {
            final OAuthRequest request = new OAuthRequest(method, SITE_URL + path);
            service.signRequest(this.accessToken, request);
            return service.execute(request);
        } catch (InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response makeRequest(String path, Verb method) {
        return makeRequest(path, method, Collections.emptyMap());
    }
}
