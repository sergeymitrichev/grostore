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

public class ApiClient {

    private final String SITE_URL;
    private final OAuth10aService service;
    private final OAuth1AccessToken accessToken;

    public ApiClient(
            String apiKey,
            String apiSecret,
            String token,
            String tokenSecret,
            String siteUrl
    ) {
        SITE_URL = siteUrl;
        service = new ServiceBuilder(apiKey)
                .apiSecret(apiSecret)
                .callback(SITE_URL)
                .debug()
                .build(UcozApi.instance());

        accessToken = new OAuth1AccessToken(token, tokenSecret);
    }

    public Response makeRequest(String path, Verb method, Map<String, String> parameters) {
        try {
            final OAuthRequest request = new OAuthRequest(method, SITE_URL + path);
            for (Map.Entry<String,String> p: parameters.entrySet()) {
                request.addQuerystringParameter(p.getKey(), p.getValue());
            }
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
