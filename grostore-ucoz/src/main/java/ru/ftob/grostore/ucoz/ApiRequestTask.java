package ru.ftob.grostore.ucoz;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class ApiRequestTask implements Callable<Response> {

    private final OAuthRequest request;

    private final OAuth10aService service;

    public ApiRequestTask(OAuthRequest request, OAuth10aService service) {
        this.request = request;
        this.service = service;
    }

    @Override
    public Response call() throws InterruptedException, ExecutionException, IOException {
        return service.execute(request);
    }
}
