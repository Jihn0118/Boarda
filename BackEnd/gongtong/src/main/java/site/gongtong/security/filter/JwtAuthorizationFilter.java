package site.gongtong.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import site.gongtong.member.model.MemberDetails;
import site.gongtong.member.service.MemberDetailsService;
import site.gongtong.security.jwt.TokenUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 지정한 URL별 JWT의 유효성 검증을 수행하며 직접적인 사용자 인증을 확인
 * <p>
 * OncePerRequestFilter 을 상속 받아 -> 요청 당 필터 한 번만 적용
 */

@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private MemberDetailsService memberDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        //1. 토큰이 필요하지 않은 api url에 대해 배열로 구성 ==> 추후 수정
        List<String> list = Arrays.asList(
                "/api/member/login",
                "/api/member/signup",
                "/api/member/checkid",
                "/api/member/checknickname",
                "/api/mypage/forgetpwd",
                "/api/mypage/profile",

                "/api/game/detail",
                "/api/game/list",
                "/api/cafe/list",
                "/api/cafe/detail",
                "/api/moim/deadline",

                "/api/ranking/games",
                "/api/ranking/cafes"
        );

        //2. 위의 list에 있는(토큰 필요x) api url의 경우 => 로직 처리없이 다음 필터로 이동
        /**
         * 3. OPTIONS 요청일 경우 -> 로직 처리 없이 다음 필터로 이동
         * OPTIONS: 주로 사전 요청이며, 서버의 상태에 영향을 주지 않는 경우가 많기 때문
         */
        if (list.contains(request.getRequestURI())
                || request.getMethod().equalsIgnoreCase("OPTIONS")) {
            filterChain.doFilter(request, response);
            return;
        }

        // [STEP.1] Client에서 API를 요청할때 쿠키를 확인
        Cookie[] cookies = request.getCookies();
        String token = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) { //쿠키 돌다가 토큰 정보가 jwt인 거 찾으면
                    token = cookie.getValue(); //토큰 정보 뽑기
                    break;
                }
            }
        }
        //요청 확인
        try {
            // [STEP.2-1] 쿠키 내에 토큰이 null이 아니고, 내용이 비지 않을 경우
            if (token != null && !token.equalsIgnoreCase("")) {

                // [STEP.2-2] 쿠키 내에있는 토큰이 유효한지 여부를 체크
                if (TokenUtils.isValidToken(token)) {
                    // [STEP.2-3] 토큰 기반으로 사용자 아이디를 반환받기
                    String id = TokenUtils.getUserIdFromToken(token);

                    // [STEP.2-4] 사용자 아이디가 존재하는지에 대한 여부 체크
                    if (id != null && !id.equalsIgnoreCase("")) {
                        MemberDetails memberDetails = memberDetailsService.loadUserByUsername(id);
//                        System.out.println("memberDetails!@!@!@!@!!@!@!@!: "+memberDetails);
                        // 전체 정보(패스워드 제외), 패스워드, 권한 리스트 => 토큰으로 뽑기
                        UsernamePasswordAuthenticationToken authentication
                                = new UsernamePasswordAuthenticationToken(memberDetails, null, memberDetails.getAuthorities());
                        // 현재 사용자의 인증 정보를 설정
                        //SecurityContextHolder: 실행 중인 스레드의 보안 컨텍스트를 제공
                        //                      인증 및 권한 부여와 같은 보안 작업을 처리
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        filterChain.doFilter(request, response);
                    } else {
                        log.info("no user like that");
                    }
                }
                // [STEP.2-5] 토큰이 유효하지 않은 경우
                else {
                    log.info("token not valid");
                }
            }
            // [STEP.3] 토큰이 존재하지 않는 경우
            else {
                log.info("token not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
