package ru.ftob.grostore.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ru.ftob.grostore.security.JWTAuthenticationFilter;
import ru.ftob.grostore.security.JWTLoginFilter;
import ru.ftob.grostore.security.SsoFilterBuilder;

import javax.servlet.Filter;
import java.util.Arrays;

import static ru.ftob.grostore.rest.config.RestConstants.*;

@Configuration
@EnableWebSecurity
@EnableOAuth2Client
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PropertySource(value = REST_OAUTH_SECURITY_PROPERTIES_FILE, ignoreResourceNotFound = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final SsoFilterBuilder filterBuilder;

    @Autowired
    public SecurityConfiguration(SsoFilterBuilder filterBuilder) {
        this.filterBuilder = filterBuilder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_URL_PATTERN, ERROR_URL_PATTERN, LOGOUT_URL)
                .permitAll()
//                .anyRequest().authenticated()
//                .anyRequest().hasAuthority(ROLE_ADMIN.toString())

                .and().logout()
                    .deleteCookies(J_SESSION_ID)
                    .logoutUrl(LOGOUT_URL)
                    .logoutSuccessUrl(LOGOUT_SUCCESS_URL);

        http.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
        http.addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(authTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

    private Filter ssoFilter() {
        return filterBuilder.add(GOOGLE_LOGIN_URL, googleClient(), googleResource()).buildAndGet();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConfigurationProperties(GOOGLE_CLIENT_KEY)
    public AuthorizationCodeResourceDetails googleClient() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties(GOOGLE_RESOURCE_KEY)
    public ResourceServerProperties googleResource() {
        return new ResourceServerProperties();
    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(
            OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("accept", "authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public JWTAuthenticationFilter authTokenFilterBean() throws Exception {

        return new JWTAuthenticationFilter();
    }

    @Bean
    public JWTLoginFilter loginFilter() throws Exception {

        return new JWTLoginFilter("/login", authenticationManager());
    }

    @Bean
    public FilterRegistrationBean jwtLoginFilterRegister(JWTLoginFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean
    public FilterRegistrationBean jwtAuthTokenFilterRegister(JWTAuthenticationFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setEnabled(false);
        return registration;
    }
}
