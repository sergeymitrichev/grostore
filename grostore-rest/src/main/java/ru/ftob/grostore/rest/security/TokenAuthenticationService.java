package ru.ftob.grostore.rest.security;

import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import static java.util.Collections.emptyList;

@Service
public class TokenAuthenticationService {

    private final AuthenticationUserService authenticationUserService;

    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    @Value("${jwt.expiration.time}")
    private Long GWT_EXPIRATION_TIME;

    @Value("${jwt.secret}")
    private String GWT_SECRET;

    private String GWT_TOKEN_NAME = "jwt-suu-token";

    @Autowired
    public TokenAuthenticationService(AuthenticationUserService authenticationUserService) {
        this.authenticationUserService = authenticationUserService;
    }

    public void addAuthentication(HttpServletResponse res, AuthUserDetails user)
            throws UnsupportedEncodingException {

        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("email", user.getUsername());
        String JWT =
                Jwts.builder()
                        .setClaims(claims)
                        .setExpiration(new Date(System.currentTimeMillis() + GWT_EXPIRATION_TIME))
                        .signWith(SignatureAlgorithm.HS512, GWT_SECRET)
                        .compact();

        Cookie cookie = new Cookie(GWT_TOKEN_NAME, URLEncoder.encode(TOKEN_PREFIX + " " + JWT, "UTF-8"));
        cookie.setSecure(false);
        cookie.setHttpOnly(false);
        cookie.setMaxAge(new Long(GWT_EXPIRATION_TIME / 1000L).intValue()) ;
        cookie.setPath("/");
        res.addCookie(cookie);
    }

    public Authentication getAuthentication(HttpServletRequest request)
            throws UnsupportedEncodingException {

        String token = null;

        Cookie[] cookies = request.getCookies();
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if (cookies[i].getName().equals(GWT_TOKEN_NAME)) {
                token = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
            }
        }

        if (token != null) {
            Claims claims = null;
            try {
                claims =
                        Jwts.parser()
                                .setSigningKey(GWT_SECRET)
                                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                                .getBody();
            } catch (ExpiredJwtException e) {
                return null;
            }
            AuthUserDetails userDetails =
                    (AuthUserDetails)
                            authenticationUserService.loadUserByUsername(claims.get(USERNAME).toString());

            if (userDetails != null) {
                return new UsernamePasswordAuthenticationToken(userDetails, null, emptyList());
            }
        }
        return null;
    }
}
