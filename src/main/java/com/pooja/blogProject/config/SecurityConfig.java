package com.pooja.blogProject.config;

import com.pooja.blogProject.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.beans.Customizer;

@Configuration
public class SecurityConfig {

    @Autowired
    public UserDetailService userDetailService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(Customizer -> Customizer.disable())
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/user/login", "/register","/css/**", "/js/**", "/images/**").permitAll()
                .anyRequest().authenticated()
        )
                .formLogin(form -> form
                        .loginPage("/user/login")
                        .usernameParameter("email")
                        .defaultSuccessUrl("/dashboard", true)
                );;
                return http.build();
    }



    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }
}



