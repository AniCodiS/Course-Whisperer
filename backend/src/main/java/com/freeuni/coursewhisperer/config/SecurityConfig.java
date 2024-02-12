//package com.freeuni.coursewhisperer.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests(requests -> requests
//                        .requestMatchers(HttpMethod.POST, "/api/").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/").authenticated()
//                        .requestMatchers(HttpMethod.PUT, "/api/").authenticated()
//                        .requestMatchers(HttpMethod.DELETE, "/api/").authenticated()
//                        .anyRequest().permitAll())
//                .httpBasic();
//        return http.build();
//    }
//}