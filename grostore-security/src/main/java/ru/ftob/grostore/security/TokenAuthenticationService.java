package ru.ftob.grostore.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private AuthenticationUserService authenticationUserService;


    static final long EXPIRATION_TIME = 864_000_000; // 10 days
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String TOKEN_COOKIE_NAME = "gs-jwt-token";
    static final String HEADER_STRING = "Authorization";

    public void addAuthentication(HttpServletResponse res, UserDetails user)
            throws UnsupportedEncodingException {

        Claims claims = Jwts.claims().setSubject(user.getUsername());
        GrantedAuthority authority = (GrantedAuthority) user.getAuthorities().toArray()[0];
        claims.put("email", user.getUsername());
        claims.put("enabled", user.isEnabled());
        claims.put("ROLE", authority != null ? authority.getAuthority() : "");
        String JWT =
                Jwts.builder()
                        .setClaims(claims)
                        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                        .signWith(SignatureAlgorithm.HS512, SECRET)
                        .compact();

        Cookie cookie = new Cookie(TOKEN_COOKIE_NAME, URLEncoder.encode(TOKEN_PREFIX + " " + JWT, "UTF-8"));
        cookie.setSecure(false);
        cookie.setHttpOnly(false);
        cookie.setMaxAge(new Long(EXPIRATION_TIME / 1000L).intValue());
        //    cookie.setDomain("localhost");
        cookie.setPath("/");
        res.addCookie(cookie);
    }

    public Authentication getAuthentication(HttpServletRequest request)
            throws UnsupportedEncodingException {

        // String token = request.getHeader( HEADER_STRING );
        String token = null;

        Cookie[] cookies = request.getCookies();
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if (cookies[i].getName().equals(TOKEN_COOKIE_NAME)) {
                token = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
            }
        }

        if (token != null) {

            // parse the token.
            Claims claims = null;
            try {
                claims =
                        Jwts.parser()
                                .setSigningKey(SECRET)
                                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                                .getBody();
            } catch (ExpiredJwtException e) {
                return null;
            }
            UserDetails userDetails =
                            authenticationUserService.loadUserByUsername(claims.get("email").toString());

            if (userDetails != null) {
                return new UsernamePasswordAuthenticationToken(userDetails, null, emptyList());
            }
        }
        return null;
    }
}

