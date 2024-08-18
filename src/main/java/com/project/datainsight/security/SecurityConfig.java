package com.project.datainsight.security;

import com.project.datainsight.login.filter.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    //BCryptPasswordEncoder:
    //비밀번호를 해싱하고 비교하는 데 사용되는 Spring Security의 클래스
    //버전 이슈로 직접 Bean 에 등록해줌
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //formLogin : 기본 제공되는 폼 로그인 방식 비활성화
        //httpBasic : HTTP Basic 인증 비활성화(보안에 취약하므로 JWT와 같은 암호화 기반 토큰인증방식 사용 시 비활성화 처리)
        //csrf : Cross-Site Request Factory 보호를 비활성화
        http
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable);

        //requestMatchers("/login", "/api", "/user").permitAll()
        // /login, /api, /join 경로는 인증 없이 접근할 수 있도록 허용
        // anyRequest().authenticated()
        // 위에서 정의된 경로를 제외한 모든 요청은 인증이 필요
        http
                .authorizeHttpRequests((auth) -> auth
                    .requestMatchers("/login", "/api", "/user").permitAll()
                    .anyRequest().authenticated()
                );

        //sessionManagement() : 세션 관리 정책 설정
        //SessionCreationPolicy.STATELESS
        // 서버에서 세션을 생성하지 않도록 설정, JWT 를 사용할 목적이고, JWT는 상태정보를 저장하지 않는 Stateless한 특성이 있음
        // 스프링 시큐리티는 기본적으로 인증이 필요한 요청에 대해 세션을 생성하고 관리하는 기능을 제공하는데, JWT 인증 방식에서는 세셩을 생성할 필요가 없기 때문
        http
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //http.addFilter()
        // LoginFilter를 UsernamePasswordAuthenticationFilter의 위치에 추가. 이 필터는 사용자의 로그인 요청을 처리함
        http.addFilter(new LoginFilter(authenticationManager(authenticationConfiguration)));
        return http.build();
    }


}
