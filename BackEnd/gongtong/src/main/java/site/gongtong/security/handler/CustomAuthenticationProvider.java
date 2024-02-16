package site.gongtong.security.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import site.gongtong.member.model.MemberDetails;
import site.gongtong.member.service.MemberDetailsService;

/**
 * 사용자의 인증을 처리하는 역할
 */

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final MemberDetailsService memberDetailsService;

    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {// 사용자 인증의 핵심 부분
        log.debug("2. Custom AuthenticaitonProvider");

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        // AuthenticationFilter에서 생성된 토큰으로부터 ID, PW를 조회
        String id = token.getName();
        String password = (String) token.getCredentials();

        // Spring security - UserDetailsService를 통해 DB에서 username으로 사용자 조회
        MemberDetails securityMemberDetailsDto = memberDetailsService.loadUserByUsername(id);

        // 대소문자를 구분하는 matches() 메서드로 db와 사용자가 제출한 비밀번호를 비교
        if (!bCryptPasswordEncoder().matches(password, securityMemberDetailsDto.getUsername())) {
            throw new BadCredentialsException(securityMemberDetailsDto.getUsername() + "Invalid password");
        }

        // 인증이 성공하면
        // 인증된 사용자의 정보, 비밀번호, 권한을 담은
        // 새로운 Token을 반환
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(securityMemberDetailsDto, password, securityMemberDetailsDto.getAuthorities());
        return authToken;
    }

    @Override
    public boolean supports(Class<?> authentication) { //AuthenticationProvider가 특정 Authentication 타입을 지원하는지 여부를 반환
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
