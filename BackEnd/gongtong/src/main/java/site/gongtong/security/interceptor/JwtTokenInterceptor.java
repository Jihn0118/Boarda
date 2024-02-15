package site.gongtong.security.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import site.gongtong.security.jwt.TokenUtils;

/**
 * http 요청이 컨트롤러에 도달하기 전에 실행되는 로직
 * JWT 토큰의 유효성을 검사하는 역할
 * => 유효 토큰x or 토큰이 전혀 없음 : 요청 거부
 * => 유효한 토큰이 있음 : 요청 통과
 */

@Slf4j
@Component
public class JwtTokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) {

        String token = null;

        //쿠키에서 JWT 토큰 가져오기
        jakarta.servlet.http.Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (jakarta.servlet.http.Cookie cookie : cookies) {
                if (cookie.getName().equals("jwt")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token != null) {
            if (TokenUtils.isValidToken(token)) {
                String id = TokenUtils.getUserIdFromToken(token);
                if (id == null) {
                    log.info("token isn't id,,,?");
                }
                return true; //id 제대로 들어있음
            } else {
                log.info("token is invalid");
            }
        } else {
            log.info("token is null");
        }
        return false;
    }
}
