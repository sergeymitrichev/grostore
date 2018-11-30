package ru.ftob.grostore.ucoz;

import com.github.scribejava.apis.UcozApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.*;

@Service
public class ApiClient {

    private final String SITE_URL;
    private final Long REQUEST_DELAY = 2L;
    private final TimeUnit REQUEST_DELAY_UNIT = TimeUnit.SECONDS;
    private final OAuth10aService service;
    private final OAuth1AccessToken accessToken;
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public ApiClient(
            @Value("${ucoz.apiKey}") String apiKey,
            @Value("${ucoz.apiSecret}") String apiSecret,
            @Value("${ucoz.token}") String token,
            @Value("${ucoz.tokenSecret}") String tokenSecret,
            @Value("${ucoz.siteUrl}") String siteUrl
    ) {
        SITE_URL = siteUrl;
        service = new ServiceBuilder(apiKey)
                .apiSecret(apiSecret)
                .callback(SITE_URL)
//                .debug()
                .build(UcozApi.instance());

        accessToken = new OAuth1AccessToken(token, tokenSecret);
    }

    public Response makeRequest(String path, Verb method, Map<String, String> parameters) throws ExecutionException, InterruptedException {
        final OAuthRequest request = new OAuthRequest(method, SITE_URL + path);
        for (Map.Entry<String, String> p : parameters.entrySet()) {
            switch (method) {
                case GET: {
                    request.addQuerystringParameter(p.getKey(), p.getValue());
                    break;
                }
                case POST: {
                    request.addBodyParameter(p.getKey(), p.getValue());
                    break;
                }
            }
        }
        service.signRequest(this.accessToken, request);
        ScheduledFuture<Response> future = executor.schedule(new ApiRequestTask(request, service), REQUEST_DELAY, REQUEST_DELAY_UNIT);
        return future.get();
    }

    public Response makeRequest(String path, Verb method) throws ExecutionException, InterruptedException {
        return makeRequest(path, method, Collections.emptyMap());
    }


}
