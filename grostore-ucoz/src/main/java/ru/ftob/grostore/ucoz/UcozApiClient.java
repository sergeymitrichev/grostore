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
import java.util.concurrent.ExecutionException;

public class UcozApiClient {

    private final String SITE_URL;
    private final OAuth10aService service;

    public UcozApiClient(
            String apiKey,
            String apiSecret,
            String token,
            String tokenSecret,
            String siteUrl
                         ) {
        service = new ServiceBuilder(apiKey)
                .apiSecret(apiSecret)
                .debug()
                .build(UcozApi.instance());
        SITE_URL = siteUrl;
    }

    public Response makeRequest(String path, Verb method, Map<String,String> parameters) {
        try {
            final OAuth1RequestToken requestToken = service.getRequestToken();
            final OAuth1AccessToken accessToken = service.getAccessToken(requestToken, "oauthVerifier");
            final OAuthRequest request = new OAuthRequest(method, path);
            service.signRequest(accessToken, request);
            return service.execute(request);
        } catch (InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Response makeRequest(String path, Verb method) {
        return makeRequest(path, method, (HashMap<String, String>) Collections.EMPTY_MAP);
    }
}
