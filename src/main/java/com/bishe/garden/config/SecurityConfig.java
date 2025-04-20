package com.bishe.garden.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // 禁用CSRF保护
            .authorizeHttpRequests()
                .anyRequest().permitAll()  // 允许所有请求
            .and()
            .httpBasic().disable()  // 禁用基本认证
            .formLogin().disable()  // 禁用表单登录
            .logout().disable();  // 禁用登出
            
        return http.build();
    }
} 