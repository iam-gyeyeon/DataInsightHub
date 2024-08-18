package com.project.datainsight.login.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * LoginFilter
 * UsernamePasswordAuthenticationFilter를 상속받아 사용자 이름과 비밀번호를 기반으로 인증을 수행하는 필터
 */
@AllArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    /**
     * 이 메서드는 인증을 시도하는 로직을 정의한다.
     * 사용자 이름과 비밀번호를 추출한 후, UsernamePasswordAuthenticationToken을 생성하여 AuthenticationManager를 통해 인증을 시도.
     *
     * 사용자명과 비밀번호로 인증을 시도할 때 호출, 주로 여기서 인증처리 위임 로직을 구현한다.
     * 정상적으로 로그인 된다면 attemptAuthentication() -> successfulAuthentication() 순서로 동작
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // obtain XXX : 요청 객체에서 사용자 이름을 추출한다. 메서드는 부모 클래스에서 제공하는 메서드로,
        // 기본적으로 사용자 이름(+비밀번호 등)을 요청의 파라미터에서 가져온다.
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        //사용자 이름과 비밀번호 기반으로 인증 토큰을 생성 (토큰은 인증과정에서 사용된다)
        //세번째 매개변수는 권한을 나타내는데, 초기는 null
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password, null);

        return authenticationManager.authenticate(token);
    }

    /**
     * 인증 성공 시 호출
     * 응답 생성 및 후속 처리를 위한 구현
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {
        System.out.println("[Authentication Result] success");
    }

    /**
     * 인증 실패 시 호출
     * 실패 사유에 따른 예 처리 및 응답값 제어
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        System.out.println("[Authentication Result] Fail");
    }
}
