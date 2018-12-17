package ru.ftob.grostore.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;


public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger log = LoggerFactory.getLogger(JWTLoginFilter.class);

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {

        AccountCredentials userDetails = new ObjectMapper().readValue(req.getInputStream(), AccountCredentials.class);

        log.debug("User with email:'{}' tries to login." +
                " Validating credentials...", userDetails.getEmail());

        Authentication authenticate;

        authenticate = getAuthenticationManager()
                .authenticate(new UsernamePasswordAuthenticationToken(userDetails.getEmail(),
                        userDetails.getPassword(), Collections.emptyList()));

        return authenticate;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        if (auth instanceof UsernamePasswordAuthenticationToken) {
            if (auth.getPrincipal() instanceof UserDetailsImpl) {
                UserDetailsImpl details = (UserDetailsImpl) auth.getPrincipal();
                log.debug("User with username:'{}' successful authentication.", details.getUsername());
            }
        }

        tokenAuthenticationService.addAuthentication(res, (UserDetailsImpl) auth.getPrincipal());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
