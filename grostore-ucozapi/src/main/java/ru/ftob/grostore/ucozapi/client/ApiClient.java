package ru.ftob.grostore.ucozapi.client;

import com.github.scribejava.apis.UcozApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth10aService;
import ru.ftob.grostore.ucozapi.Parameters;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class ApiClient {

    private static final String SITE_URL = "";
    private static final OAuth10aService service = new ServiceBuilder("your_api_key")
            .apiSecret("your_api_secret")
            .debug()
            .build(UcozApi.instance());

    private ApiClient() {
    }

    public Response makeRequest(String path, Verb method, Parameters parameters) {
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
        return makeRequest(path, method, null);
    }
}
