package ru.ftob.grostore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

@Component
public class SsoFilterBuilder {

    private final String USER_IDENTITY_FIELD_NAME = "email";

    private final OAuth2ClientContext oAuth2ClientContext;

    private final AuthenticationUserService authenticationUserService;

    private final List<Filter> filters = new ArrayList<>();

    @Autowired
    public SsoFilterBuilder(OAuth2ClientContext oAuth2ClientContext, AuthenticationUserService authenticationUserService) {
        this.oAuth2ClientContext = oAuth2ClientContext;
        this.authenticationUserService = authenticationUserService;
    }

    public SsoFilterBuilder add(String path, AuthorizationCodeResourceDetails client, ResourceServerProperties resource) {
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);

        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(client, oAuth2ClientContext);
        filter.setRestTemplate(restTemplate);

        UserInfoTokenServices tokenServices = new UserInfoTokenServices(resource.getUserInfoUri(), client.getClientId());
        tokenServices.setAuthoritiesExtractor(authoritiesExtractor());
        tokenServices.setPrincipalExtractor(principalExtractor());
        tokenServices.setRestTemplate(restTemplate);
        filter.setTokenServices(tokenServices);

        filters.add(filter);
        return this;
    }

    public Filter buildAndGet() {
        CompositeFilter compositeFilter = new CompositeFilter();
        compositeFilter.setFilters(filters);
        filters.clear();
        return compositeFilter;
    }

    private PrincipalExtractor principalExtractor() {
        return map -> {
            String email = (String) map.get(USER_IDENTITY_FIELD_NAME);
            return authenticationUserService.loadUserByUsername(email);
        };
    }

    private AuthoritiesExtractor authoritiesExtractor() {
        return map -> {
            String email = (String) map.get(USER_IDENTITY_FIELD_NAME);
            UserDetails user = authenticationUserService.loadUserByUsername(email);
            return new ArrayList<>(user.getAuthorities());
        };
    }
}
