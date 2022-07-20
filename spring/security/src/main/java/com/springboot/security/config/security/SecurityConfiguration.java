package com.springboot.security.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public SecurityConfiguration(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic().disable() // UI 사용 설정 비활성화

                // CSRF 보안 비활성화
                .csrf().disable()

                // 앱 동작방식, 세션 STATELESS
                .sessionManagement()
                .sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                )

                .and()
                .authorizeRequests()// 앱 요청 사용 권한 체크
                .antMatchers("/sign-api/sign-in", "/sign-api/sign-up", "/sign-api/exception").permitAll() // 허용
                .antMatchers(HttpMethod.GET, "/product/**").permitAll() // GET 요청 허용

                // exception 포함 경로 허용
                .antMatchers("**exceptiion**").permitAll()

                // 기타 요청은 ADMIN 사용자에게 허용
                .anyRequest().hasRole("ADMIN")

                .and()
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler()) // 권한 확인 과정에서 예외 발생 시 전달
                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint()) // 인증 과정에서 예외 발생 시 전달

                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    // WebSecurity는 HttpSecurity 앞단에 적용 (인증, 인가 적용 전)
    // Swagger 관련 경로에 인증, 인가 예외
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return webSecurity -> webSecurity.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**",
                "/swagger-ui/**", "/webjars/**", "/swagger/**", "/sign-api/exception");
    }
}
