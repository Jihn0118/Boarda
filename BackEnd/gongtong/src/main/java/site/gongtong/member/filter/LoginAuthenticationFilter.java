package site.gongtong.member.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import site.gongtong.member.dto.LoginRequest;

import java.io.IOException;

/**
 * 로그인 처리 제일 진입부
 * aA() 호출해서 '인증 요청 시작'
 * 결과 를 '인증/미인증 처리하는 핸들러'에게 넘김
 */
public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public LoginAuthenticationFilter(final String defaultFilterProcessUrl,
                                     final AuthenticationManager authenticationManager) { //인증(로그인) 수행하는 인터페이스
        super(defaultFilterProcessUrl, authenticationManager); //필터가 처리할 url, 이 필터가 사용할 설정
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String method = request.getMethod(); //get인지 post인지

        if (!method.equals("POST")) { //로그인인데 포스트 요청이 아니야?
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        ServletInputStream inputStream = request.getInputStream(); //post방식으로 들어온 데이터 읽음 (jsson형식)

        LoginRequest loginRequest = new ObjectMapper().readValue(inputStream, LoginRequest.class); //json을 자바 객체로 

        System.out.println("Can i make UPAToken??!?!?!"); //나오는지 확인
        return this.getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken( //사용자 인증 시도 - 사용자의 인증 정보
                        loginRequest.getId(),
                        loginRequest.getPassword() //이 두 내용으로 UPA토큰 발급
                )
        );
    }
}
