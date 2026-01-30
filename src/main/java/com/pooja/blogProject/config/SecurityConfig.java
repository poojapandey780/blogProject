package com.pooja.blogProject.config;

import com.pooja.blogProject.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
                .requestMatchers(
                        "/user/login",
                        "/user/register",
                        "/user/forgotPassword",
                        "/user/verifyOtp",
                        "/user/resetPassword",
                        "/user/blog",
                        "/user/blog/**",
                        // 🔓 static resources
                        "/css/**",
                        "/js/**",
                        "/images/**",

                        // 🔓 uploaded images (VERY IMPORTANT)
                        "/img/**"
                ).permitAll()
                .anyRequest().authenticated()
        )
                .formLogin(form -> form
                        .loginPage("/user/login")
                        .usernameParameter("email")
                        .defaultSuccessUrl("/user/home", true)
                );
                return http.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(10));
        provider.setUserDetailsService(userDetailService);
        return provider;
    }
}



