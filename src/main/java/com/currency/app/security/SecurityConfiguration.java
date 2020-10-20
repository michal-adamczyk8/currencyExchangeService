package com.currency.app.security;

import com.currency.app.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserService userDetailsService;

    @Value("${server.servlet.context-path}")
    private static String apiPrefix;

    @Bean
    public PasswordEncoder encoder() {
        return new Pbkdf2PasswordEncoder("53cr3t");
    }

    private static final String[] PERMIT_ALL_URLS = {
            apiPrefix +  "/user/registration/**"
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(apiPrefix + "/account/**", apiPrefix + "/exchange/**")
            .access("hasRole('ROLE_USER')")
            .antMatchers(PERMIT_ALL_URLS)//"/api/v1/h2-console/**")
            .permitAll();
        http.csrf().disable();
    }
}
