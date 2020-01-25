package com.chatbot.deltour.security;

import com.chatbot.deltour.security.filter.FilterSkipMatcher;
import com.chatbot.deltour.security.filter.FormLoginFilter;
import com.chatbot.deltour.security.filter.JwtAuthenticationFilter;
import com.chatbot.deltour.security.handlers.FormLoginAuthenticationSuccessHandler;
import com.chatbot.deltour.security.handlers.JwtAuthenticationFailureHandler;
import com.chatbot.deltour.security.jwt.HeaderTokenExtractor;
import com.chatbot.deltour.security.providers.FormLoginAuthenticationProvider;
import com.chatbot.deltour.security.providers.JwtAuthenticationProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private FormLoginAuthenticationSuccessHandler formLoginAuthenticationSuccessHandler;

    @Autowired
    private FormLoginAuthenticationProvider provider;

    @Autowired
    private JwtAuthenticationProvider jwtProvider;

    @Autowired
    private JwtAuthenticationFailureHandler jwtFailureHandler;

    @Autowired
    private HeaderTokenExtractor headerTokenExtractor;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    protected FormLoginFilter formLoginFilter() throws Exception {
        FormLoginFilter filter = new FormLoginFilter("/auth/formLogin", formLoginAuthenticationSuccessHandler, null);
        filter.setAuthenticationManager(super.authenticationManagerBean());

        return filter;
    }

    protected JwtAuthenticationFilter jwtFilter() throws Exception {
        FilterSkipMatcher matcher = new FilterSkipMatcher(Arrays.asList("/auth/**"), "/api/**");
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(matcher, jwtFailureHandler, headerTokenExtractor);
        filter.setAuthenticationManager(super.authenticationManagerBean());

        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(this.provider)
                .authenticationProvider(this.jwtProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests().antMatchers("/h2-console/*", "/auth/**").permitAll()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .anyRequest().authenticated()
                .and()
                .cors()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .addFilterBefore(formLoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
